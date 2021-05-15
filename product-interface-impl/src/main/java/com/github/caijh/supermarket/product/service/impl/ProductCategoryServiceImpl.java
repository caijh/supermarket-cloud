package com.github.caijh.supermarket.product.service.impl;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.caijh.commons.util.BeanUtils;
import com.github.caijh.commons.util.DateUtils;
import com.github.caijh.framework.core.enums.CommonStatus;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.supermarket.product.model.ProductCategory;
import com.github.caijh.supermarket.product.repository.ProductCategoryRepository;
import com.github.caijh.supermarket.product.service.ProductCategoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

@Named("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Inject
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public JpaRepository<ProductCategory, Long> getRepository() {
        return this.productCategoryRepository;
    }

    @Override
    public void add(@NotNull ProductCategory category) {
        category.setCreateTime(DateUtils.now());
        category.setStatus(CommonStatus.IN_USE.getIndex());
        this.getRepository().save(category);
    }

    @Override
    public void update(@Nonnull ProductCategory category) throws BizException {
        ProductCategory oldCategory = this.getRepository().findById(category.getId())
                                          .orElseThrow(() -> BizException.of("product.category.not_found"));
        BeanUtils.copyIgnoreNullProperties(category, oldCategory);
        this.getRepository().save(oldCategory);
    }

}
