package com.coding.gateway.auth.oauth2.client.token;

import java.util.Collection;

import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@EqualsAndHashCode(callSuper = false)
public class OAuth2AuthenticationToken extends UsernamePasswordAuthenticationToken {

    private transient OAuth2AccessToken accessToken;

    public OAuth2AuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority>
        authorities, OAuth2AccessToken accessToken) {
        super(principal, credentials, authorities);
        this.accessToken = accessToken;
    }

    public OAuth2AccessToken getAccessToken() {
        return accessToken;
    }
}
