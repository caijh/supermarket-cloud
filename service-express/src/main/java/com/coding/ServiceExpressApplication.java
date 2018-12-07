package com.coding;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan(basePackages = {"com.coding.supermarket.domain.express"}, markerInterface = Mapper.class)
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceExpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceExpressApplication.class, args);
    }
}
