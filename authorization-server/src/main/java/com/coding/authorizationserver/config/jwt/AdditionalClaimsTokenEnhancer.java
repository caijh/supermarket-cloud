package com.coding.authorizationserver.config.jwt;

import java.util.HashMap;
import java.util.Map;

import com.coding.commons.domain.client.model.ClientUserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class AdditionalClaimsTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        ClientUserDetails clientUserDetails = (ClientUserDetails) authentication.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", clientUserDetails.getUser().getId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
        return accessToken;
    }
}
