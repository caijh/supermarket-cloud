package com.coding.authorizationserver.config.oauth;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;

@Named
public class AccessTokenRevocationServiceImpl implements TokenRevocationService {
    @Inject
    private ConsumerTokenServices consumerTokenServices;

    @Override
    public void revoke(String token) {
        consumerTokenServices.revokeToken(token);
    }

    @Override
    public boolean supports(String tokenType) {
        return "access_token".equalsIgnoreCase(tokenType);
    }
}
