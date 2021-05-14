package com.github.caijh.supermarket.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.caijh.supermarket.product.model.Product;
import com.github.caijh.supermarket.product.model.ProductSku;
import com.github.caijh.supermarket.product.repository.BrandRepository;
import com.github.caijh.supermarket.product.repository.CategoryRepository;
import com.github.caijh.supermarket.product.repository.ProductRepository;
import com.github.caijh.supermarket.product.repository.ProductSkuExtRepository;
import com.github.caijh.supermarket.product.repository.ProductSkuRepository;
import com.github.caijh.supermarket.product.service.ProductService;
import com.github.caijh.supermarket.product.vo.ProductSkuVo;
import com.github.caijh.supermarket.product.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productCacheRepository;

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
        return this.productCacheRepository;
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
        return this.productCacheRepository.findById(id)
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

}
