package com.coding.gateway.auth.oauth2.client;

import java.net.MalformedURLException;

import com.coding.commons.base.BizException;
import org.springframework.http.ResponseEntity;

public interface OAuth2LogoutHandler {
    ResponseEntity<String> logout(String clientId, String token, String tokenType) throws BizException, MalformedURLException;
}
