package com.coding.authorizationserver.config.security;

import org.springframework.security.core.AuthenticationException;

public class CaptchaAuthenticationException extends AuthenticationException {
    public CaptchaAuthenticationException(String msg) {
        super(msg);
    }
}
