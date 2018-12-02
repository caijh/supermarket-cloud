package com.coding.supermarket.domain.product.service;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.coding.commons.base.BizException;
import com.coding.commons.util.AssertUtils;
import com.coding.commons.util.BeanUtils;
import com.coding.supermarket.domain.product.model.ProductAttrLabel;
import com.coding.supermarket.domain.product.repository.ProductAttrLabelCacheRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@Named("productAttrLabelService")
public class ProductAttrLabelServiceImpl implements ProductAttrLabelService {
    @Inject
    private ProductAttrLabelCacheRepository productAttrLabelCacheRepository;

    @Override
    public JpaRepository<ProductAttrLabel, String> getRepository() {
        return productAttrLabelCacheRepository;
    }

    @Transactional
    @Override
    public void add(ProductAttrLabel productAttrLabel) throws BizException {
        if (productAttrLabel.getId() != null) {
            throw new BizException("id must be null");
        }

        AssertUtils.notNull(productAttrLabel.getLabel());
        AssertUtils.notNull(productAttrLabel.getName());

        Optional<ProductAttrLabel> attrLabelOptional = getRepository().findById(productAttrLabel.getId());
        if (attrLabelOptional.isPresent()) {
            throw new BizException("id exist");
        }

        getRepository().save(productAttrLabel);
    }

    @Transactional
    @Override
    public void update(ProductAttrLabel productAttrLabel) throws BizException {
        ProductAttrLabel oldProductAttrLabel = getRepository().findById(productAttrLabel.getId()).orElseThrow(() -> new BizException("not found"));
        BeanUtils.copyIgnoreNullProperties(productAttrLabel, oldProductAttrLabel);
        getRepository().save(oldProductAttrLabel);
    }
}
