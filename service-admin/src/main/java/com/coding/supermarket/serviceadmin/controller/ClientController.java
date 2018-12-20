package com.coding.supermarket.serviceadmin.controller;

import com.coding.supermarket.serviceadmin.config.ClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private ClientConfiguration conf;

    @GetMapping("/ping")
    public String ping() {
        return conf.showProperties();
    }
}