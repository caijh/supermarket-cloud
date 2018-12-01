package com.coding.commons.domain.client.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.coding.commons.base.IndexEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

public class ClientDetailsWrapper implements ClientDetails {
    private ClientApp clientApp;

    public ClientDetailsWrapper(ClientApp clientApp) {
        this.clientApp = clientApp;
    }

    public ClientApp getClientApp() {
        return clientApp;
    }

    @Override
    public String getClientId() {
        return clientApp.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return clientApp.getResourceIds();
    }

    @Override
    public boolean isSecretRequired() {
        return ClientType.CONFIDENTIAL == IndexEnum.valueOf(clientApp.getClientType(), ClientType.class);
    }

    @Override
    public String getClientSecret() {
        return clientApp.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return !clientApp.getScope().isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return clientApp.getScope();
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return clientApp.getAuthorizedGrantTypes();
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return clientApp.getRedirectUri();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return clientApp.getAuthorities().parallelStream().map(authority -> (GrantedAuthority) () -> authority).collect(Collectors.toList());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return clientApp.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return clientApp.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return clientApp.getAdditionalInformation();
    }
}
