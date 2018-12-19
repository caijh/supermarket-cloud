package com.coding.supermarket.domain.product.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.coding.commons.base.BizException;
import com.coding.commons.util.AssertUtils;
import com.coding.commons.util.BeanUtils;
import com.coding.commons.util.CollectionUtils;
import com.coding.supermarket.domain.product.model.CategoryProductAttr;
import com.coding.supermarket.domain.product.repository.CategoryProductAttrCacheRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

@Named("productAttrLabelService")
public class CategoryProductAttrServiceImpl implements CategoryProductAttrService {
    private static final List<Integer> CATEGORY_PRODUCT_ATTR_TYPES = Arrays.asList(0, 1, 2, 3);
    @Inject
    private CategoryProductAttrCacheRepository categoryProductAttrCacheRepository;

    @Override
    public JpaRepository<CategoryProductAttr, Long> getRepository() {
        return categoryProductAttrCacheRepository;
    }

    @Transactional
    @Override
    public void add(CategoryProductAttr categoryProductAttr) throws BizException {
        AssertUtils.isTrue(categoryProductAttr.getId() == null, () -> new BizException("id must be null"));
        AssertUtils.isTrue(CATEGORY_PRODUCT_ATTR_TYPES.contains(categoryProductAttr.getType()), () -> new BizException("id must be null"));
        AssertUtils.notNull(categoryProductAttr.getName());
        AssertUtils.isTrue(CollectionUtils.isNotEmpty(categoryProductAttr.getValues()), () -> new BizException(""));

        CategoryProductAttr params = new CategoryProductAttr();
        params.setName(categoryProductAttr.getName());
        Optional<CategoryProductAttr> attrLabelOptional = getRepository().findOne(Example.of(params));
        if (attrLabelOptional.isPresent()) {
            throw new BizException("id exist");
        }

        getRepository().save(categoryProductAttr);
    }

    @Transactional
    @Override
    public void update(CategoryProductAttr categoryProductAttr) throws BizException {
        CategoryProductAttr oldProductAttrLabel = getRepository().findById(categoryProductAttr.getId()).orElseThrow(() -> new BizException("not found"));
        BeanUtils.copyIgnoreNullProperties(categoryProductAttr, oldProductAttrLabel);
        getRepository().save(oldProductAttrLabel);
    }
}
