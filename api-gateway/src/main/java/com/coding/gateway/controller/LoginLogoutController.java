package com.coding.gateway.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coding.commons.base.BizException;
import com.coding.gateway.auth.OAuth2AuthenticationProvider;
import com.coding.gateway.auth.oauth2.client.OAuth2LogoutHandler;
import com.coding.gateway.auth.oauth2.client.token.OAuth2UsernamePasswordAuthenticationToken;
import com.coding.gateway.constants.Urls;
import com.coding.gateway.context.ThreadLocalHolder;
import com.coding.gateway.request.LoginReqBody;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginLogoutController {

    @Inject
    private OAuth2AuthenticationProvider oauth2AuthenticationProvider;

    @Inject
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Inject
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Inject
    private OAuth2LogoutHandler oauth2LogoutHandler;

    /**
     * 登录.
     *
     * @param loginReqBody login request body
     * @param request      HttpServletRequest
     * @param response     HttpServletResponse
     * @throws IOException      IOException
     * @throws ServletException IOException
     */
    @PostMapping(value = Urls.LOGIN_ENDPOINT)
    public void login(@RequestBody LoginReqBody loginReqBody, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Authentication authentication;
        try {
            authentication = new OAuth2UsernamePasswordAuthenticationToken(ThreadLocalHolder.getClientId(), loginReqBody
                .getUsername(), loginReqBody.getPassword());
            authentication = oauth2AuthenticationProvider.authenticate(authentication);
            authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
        } catch (AuthenticationException e) {
            authenticationFailureHandler.onAuthenticationFailure(request, response, e);
        }
    }

    /**
     * 登出.
     *
     * @param token     token
     * @param tokenType token type
     */
    @GetMapping(value = Urls.LOGOUT_ENDPOINT)
    public ResponseEntity<String> logout(@RequestParam("token") String token, @RequestParam("token_type") String tokenType) throws BizException, MalformedURLException {
        String clientId = ThreadLocalHolder.getClientId();
        return oauth2LogoutHandler.logout(clientId, token, tokenType);
    }
}
