package com.coding.gateway.auth.oauth2.client;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.coding.commons.util.MD5Utils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientKeyGenerator;

public class CustomClientKeyGenerator implements ClientKeyGenerator {

    private static final String CLIENT_ID = "client_id";

    private static final String USERNAME = "username";


    @Override
    public String extractKey(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
        Map<String, String> values = new LinkedHashMap<>();

        values.put(CLIENT_ID, resource.getClientId());

        if (authentication != null) {
            values.put(USERNAME, authentication.getName());
        }

        try {
            return MD5Utils.md5(values.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }
    }
}
