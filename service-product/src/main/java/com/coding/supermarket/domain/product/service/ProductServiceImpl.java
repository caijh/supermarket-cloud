package com.coding.supermarket.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.util.BeanUtils;
import com.coding.supermarket.domain.product.model.Product;
import com.coding.supermarket.domain.product.model.ProductSku;
import com.coding.supermarket.domain.product.model.ProductSkuExt;
import com.coding.supermarket.domain.product.repository.BrandCacheRepository;
import com.coding.supermarket.domain.product.repository.CategoryCacheRepository;
import com.coding.supermarket.domain.product.repository.ProductCacheRepository;
import com.coding.supermarket.domain.product.repository.ProductSkuCacheRepository;
import com.coding.supermarket.domain.product.repository.ProductSkuExtCacheRepository;
import com.coding.supermarket.domain.product.vo.ProductSkuVo;
import com.coding.supermarket.domain.product.vo.ProductVo;
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
    private BrandCacheRepository brandCacheRepository;
    @Inject
    private CategoryCacheRepository categoryCacheRepository;

    @Override
    public JpaRepository<Product, Long> getRepository() {
        return productCacheRepository;
    }

    @Override
    public List<ProductSkuVo> findSkuList(List<Long> productSkuIds) {
        List<ProductSku> productSkuList = productSkuCacheRepository.findAllById(productSkuIds);

        return productSkuList.stream().map(productSku -> {
            ProductSkuVo productSkuVo = new ProductSkuVo();
            BeanUtils.copyIgnoreNullProperties(productSku, productSkuVo);
            productSkuExtCacheRepository.findById(productSku.getId()).ifPresent(productSkuExt -> BeanUtils.copyIgnoreNullProperties(productSkuExt, productSkuVo));
            ProductVo productVo = new ProductVo();
            productCacheRepository.findById(productSku.getProductId()).ifPresent(e -> {
                BeanUtils.copyIgnoreNullProperties(e, productVo);
                productVo.setBrand(brandCacheRepository.getOne(e.getBrandId()));
                productVo.setCategory(categoryCacheRepository.getOne(e.getCategoryId()));
            });
            productSkuVo.setProductVo(productVo);
            return productSkuVo;
        }).collect(Collectors.toList());
    }

}
