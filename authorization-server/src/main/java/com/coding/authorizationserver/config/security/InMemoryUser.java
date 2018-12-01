package com.coding.authorizationserver.config.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InMemoryUser {
    private String name;
    private String password;
}
