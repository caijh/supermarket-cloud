package com.coding.authorizationserver.config.oauth;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TokenRevocationServiceFactory {
    @Inject
    private List<TokenRevocationService> revocationServices;

    /**
     * create TokenRevocationService.
     *
     * @param hint access_token or refresh_token
     * @return
     */
    public TokenRevocationService create(String hint) {
        return revocationServices.stream()
            .filter(service -> service.supports(hint))
            .findFirst().orElse(noopRevocationService());
    }

    private TokenRevocationService noopRevocationService() {
        return new TokenRevocationService() {
            public boolean supports(String hint) {
                return false;
            }

            public void revoke(String token) {
                throw new UnsupportedOperationException();
            }
        };
    }
}
