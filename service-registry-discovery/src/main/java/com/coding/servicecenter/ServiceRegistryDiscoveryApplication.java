package com.coding.servicecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceRegistryDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryDiscoveryApplication.class, args);
    }

}
