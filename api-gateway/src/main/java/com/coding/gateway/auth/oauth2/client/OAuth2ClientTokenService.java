package com.coding.gateway.auth.oauth2.client;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.domain.user.model.UserToken;
import com.coding.commons.domain.user.repository.UserTokenRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientKeyGenerator;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@Named
public class OAuth2ClientTokenService implements ClientTokenServices {
    private ClientKeyGenerator keyGenerator = new CustomClientKeyGenerator();

    @Inject
    private UserTokenRepository userTokenRepository;

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
        Optional<UserToken> userToken = userTokenRepository.findByAuthenticationId(keyGenerator.extractKey(resource, authentication));
        return userToken.map(UserToken::getAccessToken).orElse(null);
    }

    @Override
    public void saveAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication,
                                OAuth2AccessToken accessToken) {
        removeAccessToken(resource, authentication);
        UserToken userToken = new UserToken();
        userToken.setAuthenticationId(keyGenerator.extractKey(resource, authentication));
        userToken.setAccessToken(accessToken);
        userTokenRepository.save(userToken);
    }

    @Override
    public void removeAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
        String key = keyGenerator.extractKey(resource, authentication);
        Optional<UserToken> userToken = userTokenRepository.findByAuthenticationId(key);
        if (userToken.isPresent()) {
            userTokenRepository.deleteByAuthenticationId(key);
        }
    }
}
