package com.github.caijh.supermarket.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.github.caijh.commons.util.Asserts;
import com.github.caijh.commons.util.Collections;
import com.github.caijh.commons.util.DateUtils;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.supermarket.product.enums.ProductStatusEnum;
import com.github.caijh.supermarket.product.model.Product;
import com.github.caijh.supermarket.product.model.ProductSku;
import com.github.caijh.supermarket.product.model.ProductSkuExt;
import com.github.caijh.supermarket.product.repository.BrandRepository;
import com.github.caijh.supermarket.product.repository.CategoryRepository;
import com.github.caijh.supermarket.product.repository.ProductRepository;
import com.github.caijh.supermarket.product.repository.ProductSkuExtRepository;
import com.github.caijh.supermarket.product.repository.ProductSkuRepository;
import com.github.caijh.supermarket.product.service.ProductService;
import com.github.caijh.supermarket.product.vo.ProductSkuVo;
import com.github.caijh.supermarket.product.vo.ProductVo;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductSkuRepository productSkuCacheRepository;

    @Inject
    private ProductSkuExtRepository productSkuExtCacheRepository;

    @Inject
    private BrandRepository brandCacheRepository;
    @Inject
    private CategoryRepository categoryCacheRepository;

    @Override
    public JpaRepository<Product, Long> getRepository() {
        return this.productRepository;
    }

    @Override
    public List<ProductSkuVo> findSkuList(List<Long> productSkuIds) {
        List<ProductSku> productSkuList = this.productSkuCacheRepository.findAllById(productSkuIds);

        return productSkuList.stream().map(productSku -> {
            ProductSkuVo productSkuVo = new ProductSkuVo();
            BeanUtils.copyProperties(productSku, productSkuVo);
            this.productSkuExtCacheRepository.findById(productSku.getId())
                                             .ifPresent(productSkuExt -> BeanUtils
                                                     .copyProperties(productSkuExt, productSkuVo));
            ProductVo productVo = this.findById(productSku.getProductId());
            productSkuVo.setProductVo(productVo);
            return productSkuVo;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductVo findById(Long id) {
        return this.productRepository.findById(id)
                                     .map(this::toProductVo)
                                     .orElse(null);
    }

    private ProductVo toProductVo(Product product) {
        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(product, productVo);
        productVo.setBrand(this.brandCacheRepository.getOne(product.getBrandId()));
        productVo.setCategory(this.categoryCacheRepository.getOne(product.getCategoryId()));
        return productVo;
    }

    @Transactional
    @Override
    public void add(@NotNull Product product) throws BizException {
        this.validate4add(product);

        product.setStatus(ProductStatusEnum.OFF_SALE.getIndex());
        product.setCreateTime(DateUtils.now());
        product.setCreatedBy(product.getCreatedBy());
        this.getRepository().save(product);

        product.getSkuList().forEach(productSku -> {
            productSku.setId(null);
            productSku.setCreatedBy(product.getCreatedBy());
            productSku.setCreateTime(product.getCreateTime());
            productSku.setStatus(ProductStatusEnum.OFF_SALE.getIndex());
            productSku.setProductId(product.getId());
            this.productSkuCacheRepository.save(productSku);
            ProductSkuExt productSkuExt = new ProductSkuExt();
            productSkuExt.setProductSkuId(productSku.getId());
            productSkuExt.setFrozenNum(0);
            productSkuExt.setSoldNum(0);
            productSkuExt.setStockNum(0);
            this.productSkuExtCacheRepository.save(productSkuExt);
        });
    }

    @Transactional
    @Override
    public void update(@Nonnull Product product) throws BizException {
        Product oldProduct = this.getRepository().findById(product.getId()).orElseThrow(() -> BizException.of("product.not_found"));
        BeanUtils.copyProperties(product, oldProduct);
        this.getRepository().save(oldProduct);
    }

    @Transactional
    @Override
    public void delete(Long productId) throws BizException {
        Product oldProduct = this.getRepository().findById(productId).orElseThrow(() -> BizException.of("product.not_found"));
        oldProduct.setStatus(ProductStatusEnum.DELETED.getIndex());
        List<ProductSku> productSkuList = this.productSkuCacheRepository
                .findAll(Example.of(ProductSku.builder().productId(productId).build()))
                .stream()
                .peek(e -> e.setStatus(ProductStatusEnum.DELETED.getIndex()))
                .collect(Collectors.toList());
        this.getRepository().save(oldProduct);
        this.productSkuCacheRepository.saveAll(productSkuList);
    }

    private void validate4add(Product product) throws BizException {
        Asserts.isTrue(product.getId() == null, () -> BizException.of("id must be null"));
        Asserts.isTrue(product.getCategoryId() != null, () -> BizException.of("category id is required"));
        Asserts.isTrue(product.getBrandId() != null, () -> new BizException("brand id is required"));
        Asserts.isTrue(Collections.isNotEmpty(product.getThumbnails()), () -> new BizException("商品图片不能为空"));
        Asserts.isTrue(StringUtils.isNotBlank(product.getDescription()), () -> new BizException("商品描述不能为空"));
        Asserts.isTrue(Collections.isNotEmpty(product.getSkuList()), () -> new BizException("sku不能空"));
    }

}
