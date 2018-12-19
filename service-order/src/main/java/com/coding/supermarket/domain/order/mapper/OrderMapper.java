package com.coding.supermarket.domain.order.mapper;

import com.coding.supermarket.domain.order.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper {

    Order get(Long id);

    void add(Order order);

}
