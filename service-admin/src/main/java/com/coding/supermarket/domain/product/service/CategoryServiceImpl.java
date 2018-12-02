package com.coding.supermarket.domain.product.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.BizException;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.util.BeanUtils;
import com.coding.commons.util.DateUtils;
import com.coding.supermarket.domain.product.model.Category;
import com.coding.supermarket.domain.product.repository.CategoryCacheRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@Named("productCategoryService")
public class CategoryServiceImpl implements CategoryService {

    @Inject
    @Named("productCategoryCacheRepository")
    private CategoryCacheRepository categoryCacheRepository;

    @Inject
    private LocaleMessageService localeMessageService;

    @Override
    public JpaRepository<Category, Long> getRepository() {
        return this.categoryCacheRepository;
    }

    @Override
    public void add(Category category) {
        category.setCreateTime(DateUtils.now());
        getRepository().save(category);
    }

    @Override
    public void update(Category category) throws BizException {
        Category oldCategory = getRepository().findById(category.getId()).orElseThrow(() -> localeMessageService.getLocaleException("product.category.not_found"));
        BeanUtils.copyIgnoreNullProperties(category, oldCategory);
        getRepository().save(oldCategory);
    }
}
