package com.github.caijh.supermarket.coupon.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CouponExchangeCode implements Serializable {

    private static final long serialVersionUID = -5901061827931224147L;
    private String code;

    private Integer status;

}
