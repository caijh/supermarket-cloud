package com.coding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceExpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceExpressApplication.class, args);
    }
}
