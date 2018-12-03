package com.coding.supermarket.domain.express.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Request")
@Getter
@Setter
public class SFRequest extends Request {

    @XmlAttribute
    private String lang = "zh-CN";

    @XmlAttribute
    private String service;

    @XmlElement(name = "Head")
    private String head; // 顾客编码

    @XmlElementRef(name = "Body")
    private Body body;
}
