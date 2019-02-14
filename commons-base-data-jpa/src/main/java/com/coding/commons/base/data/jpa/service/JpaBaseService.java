package com.coding.commons.base.data.jpa.service;

import java.io.Serializable;
import javax.transaction.Transactional;

import com.coding.commons.base.PersistentObject;
import com.coding.commons.base.exception.BizException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBaseService<T extends PersistentObject, I extends Serializable> {

    JpaRepository<T, I> getRepository();

    @Transactional
    default Page<T> list(T t, Pageable pageable) {
        return getRepository().findAll(Example.of(t), pageable);
    }

    @Transactional
    default T detail(I id) throws BizException {
        return getRepository().findById(id).orElseThrow(() -> new BizException("entity not found"));
    }
}
