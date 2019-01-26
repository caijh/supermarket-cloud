package com.coding.commons.base.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PageInfo<T> {

    private List<T> list;

    private Long total;

    public static <T> PageInfo<T> fromPage(Page<T> page) {
        PageInfo<T> holder = new PageInfo<>();
        holder.setList(page.getContent());
        holder.setTotal(page.getTotalElements());
        return holder;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Page<T> toPage(Pageable pageable) {
        return new PageImpl<>(list, pageable, total);
    }

}
