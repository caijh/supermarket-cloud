package com.coding.gateway.auth.oauth2.client.token;

import java.util.Objects;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class OAuth2UsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String clientId;

    public OAuth2UsernamePasswordAuthenticationToken(String clientId, Object principal, Object credentials) {
        super(principal, credentials);
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        OAuth2UsernamePasswordAuthenticationToken that = (OAuth2UsernamePasswordAuthenticationToken) o;
        return Objects.equals(clientId, that.clientId)
            && Objects.equals(this.getPrincipal(), that.getPrincipal())
            && Objects.equals(this.getCredentials(), that.getCredentials());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientId, this.getPrincipal(), this.getCredentials());
    }
}
