package com.github.caijh.supermarket.base;

import java.io.Serializable;
import javax.transaction.Transactional;

import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.framework.core.model.PersistentObject;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BaseService<T extends PersistentObject<I>, I extends Serializable> {

    JpaRepository<T, I> getRepository();

    @Transactional
    default Page<T> list(T t, Pageable pageable) {
        return this.getRepository().findAll(Example.of(t), pageable);
    }

    @Transactional
    default T detail(I id) throws BizException {
        return this.getRepository().findById(id).orElseThrow(() -> new BizException("entity " + id + "not found"));
    }

}
