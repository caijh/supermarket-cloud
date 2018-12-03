package com.coding.supermarket.domain.express.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteRequest {

    @XmlAttribute(name = "tracking_type")
    private String trackingType;

    @XmlAttribute(name = "method_type")
    private String methodType;

    @XmlAttribute(name = "tracking_number")
    private String trackingNumber;
}
