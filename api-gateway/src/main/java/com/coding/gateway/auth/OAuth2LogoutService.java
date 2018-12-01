package com.coding.gateway.auth;

import java.net.MalformedURLException;
import java.net.URL;
import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.BizException;
import com.coding.gateway.auth.oauth2.client.OAuth2LogoutHandler;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Named
public class OAuth2LogoutService implements OAuth2LogoutHandler {
    @Inject
    private OAuth2ClientProperties oauth2ClientProperties;
    @Inject
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> logout(String clientId, String token, String tokenType) throws BizException, MalformedURLException {
        OAuth2ClientProperties.Registration registration = oauth2ClientProperties.getRegistration().get(clientId);
        if (registration == null) {
            throw new BizException("client_id not register");
        }
        OAuth2ClientProperties.Provider provider = oauth2ClientProperties.getProvider().get(registration.getProvider());
        URL url = new URL(provider.getTokenUri());
        String authServerUrl = url.getProtocol() + "://" + url.getAuthority();

        return restTemplate.getForEntity(authServerUrl + "/oauth/revoke?token=" + token + "&token_type=" + tokenType, String.class);
    }
}
