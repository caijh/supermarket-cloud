package com.coding.authorizationserver.config.oauth;

public interface TokenRevocationService {
    void revoke(String token);

    boolean supports(String tokenType);
}
