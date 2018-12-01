package com.coding.gateway.auth;

import javax.inject.Named;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Named
public class CustomLogoutSuccessHandler extends HttpStatusReturningLogoutSuccessHandler {

    public CustomLogoutSuccessHandler() {
        super(HttpStatus.OK);
    }

}
