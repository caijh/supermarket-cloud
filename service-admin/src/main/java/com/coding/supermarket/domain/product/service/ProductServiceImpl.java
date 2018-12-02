package com.coding.supermarket.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.coding.commons.base.BizException;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.util.AssertUtils;
import com.coding.commons.util.BeanUtils;
import com.coding.commons.util.CollectionUtils;
import com.coding.commons.util.DateUtils;
import com.coding.commons.util.StringUtils;
import com.coding.supermarket.domain.product.model.Product;
import com.coding.supermarket.domain.product.model.ProductSku;
import com.coding.supermarket.domain.product.model.ProductSkuExt;
import com.coding.supermarket.domain.product.model.ProductStatus;
import com.coding.supermarket.domain.product.repository.ProductCacheRepository;
import com.coding.supermarket.domain.product.repository.ProductSkuCacheRepository;
import com.coding.supermarket.domain.product.repository.ProductSkuExtCacheRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductCacheRepository productCacheRepository;
    @Inject
    private ProductSkuCacheRepository productSkuCacheRepository;
    @Inject
    private ProductSkuExtCacheRepository productSkuExtCacheRepository;

    @Inject
    private LocaleMessageService localeMessageService;

    @Override
    public JpaRepository<Product, Long> getRepository() {
        return this.productCacheRepository;
    }

    @Transactional
    @Override
    public void add(Product product) throws BizException {
        validate4add(product);

        product.setStatus(ProductStatus.OFF_SALE.getIndex());
        product.setCreateTime(DateUtils.now());
        product.setCreatedBy(product.getCreatedBy());
        getRepository().save(product);

        product.getSkuList().forEach(productSku -> {
            product.setCreatedBy(product.getCreatedBy());
            product.setCreateTime(product.getCreateTime());
            productSku.setStatus(ProductStatus.OFF_SALE.getIndex());
            productSku.setProductId(product.getId());
            productSkuCacheRepository.save(productSku);
            ProductSkuExt productSkuExt = new ProductSkuExt();
            productSkuExt.setProductSkuId(productSku.getId());
            productSkuExt.setFrozenNum(0);
            productSkuExt.setSoldNum(0);
            productSkuExt.setStockNum(0);
            productSkuExtCacheRepository.save(productSkuExt);
        });
    }

    @Transactional
    @Override
    public void update(Product product) throws BizException {
        Product oldProduct = getRepository().findById(product.getId()).orElseThrow(() -> localeMessageService.getLocaleException("product.not_found"));
        BeanUtils.copyIgnoreNullProperties(product, oldProduct);
        getRepository().save(oldProduct);
    }

    @Transactional
    @Override
    public void delete(Long productId) throws BizException {
        Product oldProduct = getRepository().findById(productId).orElseThrow(() -> localeMessageService.getLocaleException("product.not_found"));
        oldProduct.setStatus(ProductStatus.DELETED.getIndex());
        List<ProductSku> productSkuList = productSkuCacheRepository.findAll(Example.of(ProductSku.builder().productId(productId).build()))
            .stream()
            .peek(e -> e.setStatus(ProductStatus.DELETED.getIndex()))
            .collect(Collectors.toList());
        getRepository().save(oldProduct);
        productSkuCacheRepository.saveAll(productSkuList);
    }

    private void validate4add(Product product) throws BizException {
        AssertUtils.isTrue(product.getId() == null, BizException.class, "id must be null");
        AssertUtils.isTrue(product.getCategoryId() != null, BizException.class, "category id is required");
        AssertUtils.isTrue(product.getBrandId() != null, BizException.class, "brand id is required");
        AssertUtils.isTrue(CollectionUtils.isNotEmpty(product.getThumbnails()), BizException.class, "商品图片不能为空");
        AssertUtils.isTrue(StringUtils.isNotBlank(product.getDescription()), BizException.class, "商品描述不能为空");
        AssertUtils.isTrue(CollectionUtils.isNotEmpty(product.getSkuList()), BizException.class, "sku不能空");
    }
}
