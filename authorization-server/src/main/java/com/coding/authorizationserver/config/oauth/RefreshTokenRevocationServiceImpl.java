package com.coding.authorizationserver.config.oauth;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Named
public class RefreshTokenRevocationServiceImpl implements TokenRevocationService {
    @Inject
    private TokenStore tokenStore;

    @Override
    public void revoke(String token) {
        OAuth2RefreshToken oAuth2RefreshToken = tokenStore.readRefreshToken(token);
        tokenStore.removeRefreshToken(oAuth2RefreshToken);
    }

    @Override
    public boolean supports(String tokenType) {
        return "refresh_token".equalsIgnoreCase(tokenType);
    }
}
