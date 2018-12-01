package com.coding.authorizationserver.controller;

import javax.inject.Inject;

import com.coding.authorizationserver.config.oauth.TokenRevocationService;
import com.coding.authorizationserver.config.oauth.TokenRevocationServiceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenRevocationController {

    @Inject
    private TokenRevocationServiceFactory tokenRevocationServiceFactory;

    /**
     * revoke token.
     *
     * @param token     token value
     * @param tokenType access_token or refresh_token
     * @return ResponseEntity
     */
    @GetMapping("/oauth/revoke")
    public ResponseEntity<String> revokeToken(@RequestParam String token, @RequestParam("token_type") String tokenType) {
        TokenRevocationService tokenRevocationService = tokenRevocationServiceFactory.create(tokenType);
        tokenRevocationService.revoke(token);
        return ResponseEntity.ok("logout success");
    }

}
