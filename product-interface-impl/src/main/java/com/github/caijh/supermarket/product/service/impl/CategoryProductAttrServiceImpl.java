package com.github.caijh.supermarket.product.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.github.caijh.commons.util.Asserts;
import com.github.caijh.commons.util.BeanUtils;
import com.github.caijh.commons.util.Collections;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.supermarket.product.model.CategoryProductAttr;
import com.github.caijh.supermarket.product.repository.CategoryProductAttrRepository;
import com.github.caijh.supermarket.product.service.CategoryProductAttrService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

@Named("productAttrLabelService")
public class CategoryProductAttrServiceImpl implements CategoryProductAttrService {

    private static final List<Integer> CATEGORY_PRODUCT_ATTR_TYPES = Arrays.asList(0, 1, 2, 3);
    @Inject
    private CategoryProductAttrRepository categoryProductAttrCacheRepository;

    @Override
    public JpaRepository<CategoryProductAttr, Long> getRepository() {
        return this.categoryProductAttrCacheRepository;
    }

    @Transactional
    @Override
    public void add(@NotNull CategoryProductAttr categoryProductAttr) throws BizException {
        Asserts.isTrue(categoryProductAttr.getId() == null, () -> new BizException("id must be null"));
        Asserts.isTrue(CategoryProductAttrServiceImpl.CATEGORY_PRODUCT_ATTR_TYPES
                .contains(categoryProductAttr.getType()), () -> new BizException("id must be null"));
        Asserts.notNull(categoryProductAttr.getName());
        Asserts.isTrue(Collections.isNotEmpty(categoryProductAttr.getValues()), () -> new BizException(""));

        CategoryProductAttr params = new CategoryProductAttr();
        params.setName(categoryProductAttr.getName());
        Optional<CategoryProductAttr> attrLabelOptional = this.getRepository().findOne(Example.of(params));
        if (attrLabelOptional.isPresent()) {
            throw new BizException("id exist");
        }

        this.getRepository().save(categoryProductAttr);
    }

    @Transactional
    @Override
    public void update(@Nonnull CategoryProductAttr categoryProductAttr) throws BizException {
        CategoryProductAttr oldProductAttrLabel = this.getRepository().findById(categoryProductAttr.getId())
                                                      .orElseThrow(() -> new BizException("not found"));
        BeanUtils.copyIgnoreNullProperties(categoryProductAttr, oldProductAttrLabel);
        this.getRepository().save(oldProductAttrLabel);
    }

}
