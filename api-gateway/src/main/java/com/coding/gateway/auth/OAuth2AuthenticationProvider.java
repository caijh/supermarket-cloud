package com.coding.gateway.auth;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.coding.commons.domain.client.model.ClientApp;
import com.coding.commons.domain.client.repository.ClientAppRepository;
import com.coding.commons.util.StringUtils;
import com.coding.gateway.auth.oauth2.client.token.OAuth2AuthenticationToken;
import com.coding.gateway.auth.oauth2.client.token.OAuth2UsernamePasswordAuthenticationToken;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import static java.util.stream.Collectors.toList;

@Named
public class OAuth2AuthenticationProvider implements AuthenticationProvider {

    @Inject
    private OAuth2ClientProperties oauth2ClientProperties;

    @Inject
    private ResourceOwnerPasswordAccessTokenProvider resourceOwnerPasswordAccessTokenProvider;

    @Inject
    private ClientAppRepository clientAppRepository;

    @Inject
    private ClientTokenServices clientTokenServices;

    @Inject
    private Environment environment;

    @Transactional
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OAuth2UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (OAuth2UsernamePasswordAuthenticationToken) authentication;
        String clientId = usernamePasswordAuthenticationToken.getClientId();
        String username = (String) usernamePasswordAuthenticationToken.getPrincipal();
        String password = (String) usernamePasswordAuthenticationToken.getCredentials();


        ResourceOwnerPasswordResourceDetails passwordResourceDetails = getResourceOwnerPasswordResourceDetails(clientId, username, password);

        OAuth2AccessToken accessToken = obtainToken(passwordResourceDetails);

        OAuth2AuthenticationToken authenticationToken = new OAuth2AuthenticationToken(username, password, getGrantedSet(accessToken), accessToken);

        clientTokenServices.saveAccessToken(passwordResourceDetails, authenticationToken, accessToken);

        return authenticationToken;
    }

    private List<GrantedAuthority> getGrantedSet(OAuth2AccessToken token) {
        return token.getScope().stream().map(e -> (GrantedAuthority) () -> e).collect(toList());
    }

    private OAuth2AccessToken obtainToken(ResourceOwnerPasswordResourceDetails passwordResourceDetails) {
        DefaultAccessTokenRequest defaultAccessTokenRequest = new DefaultAccessTokenRequest();
        OAuth2AccessToken token;
        try {
            token = resourceOwnerPasswordAccessTokenProvider.obtainAccessToken(passwordResourceDetails, defaultAccessTokenRequest);
        } catch (OAuth2AccessDeniedException accessDeniedException) {
            throw new BadCredentialsException("Invalid credentials", accessDeniedException);
        }

        return token;
    }

    private ResourceOwnerPasswordResourceDetails getResourceOwnerPasswordResourceDetails(String clientId, String
        username, String password) {
        ClientApp clientApp = clientAppRepository.findById(clientId).orElseThrow(() -> new AuthenticationServiceException("client id not found"));

        ResourceOwnerPasswordResourceDetails passwordResourceDetails = new ResourceOwnerPasswordResourceDetails();
        String applicationName = environment.getProperty("spring.application.name");
        passwordResourceDetails.setId(applicationName);
        passwordResourceDetails.setClientId(clientApp.getClientId());
        passwordResourceDetails.setClientSecret(clientApp.getClientSecret());
        passwordResourceDetails.setGrantType("password");
        passwordResourceDetails.setPassword(password);
        passwordResourceDetails.setUsername(username);
        passwordResourceDetails.setScope(new ArrayList<>(clientApp.getScope()));
        passwordResourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);

        OAuth2ClientProperties.Registration registration = oauth2ClientProperties.getRegistration().get(clientApp.getClientId());
        OAuth2ClientProperties.Provider provider;
        if (registration != null) {
            provider = oauth2ClientProperties.getProvider().get(registration.getProvider());
            passwordResourceDetails.setAccessTokenUri(provider.getTokenUri());
        } else {
            provider = oauth2ClientProperties.getProvider().get("default-auth-server");
            passwordResourceDetails.setAccessTokenUri(provider.getTokenUri());

            addRegistration(clientApp, applicationName);
        }

        return passwordResourceDetails;
    }

    private void addRegistration(ClientApp clientApp, String applicationName) {
        OAuth2ClientProperties.Registration registration;
        registration = new OAuth2ClientProperties.Registration();
        registration.setClientId(clientApp.getClientId());
        registration.setClientSecret(clientApp.getClientSecret());
        registration.setAuthorizationGrantType(StringUtils.join(clientApp.getAuthorizedGrantTypes(), ","));
        registration.setClientName(applicationName);
        registration.setScope(clientApp.getScope());
        registration.setProvider("default-auth-server");
        oauth2ClientProperties.getRegistration().put(registration.getClientId(), registration);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
