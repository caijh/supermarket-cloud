package com.coding.gateway.config;

import java.util.List;
import javax.inject.Inject;

import com.coding.gateway.auth.GrantedAuthorityFilter;
import com.coding.gateway.constants.Urls;
import com.coding.gateway.context.ThreadLocalHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String AUTH_ENDPOINT = Urls.AUTH_ENDPOINT;

    private static final List<String> ANONYMOUS_ENDPOINTS = Urls.getAnonymousEndpoints();


    @Inject
    private GrantedAuthorityFilter grantedAuthorityFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .formLogin().disable().logout().disable()
            .authorizeRequests()
            .antMatchers(ANONYMOUS_ENDPOINTS.toArray(new String[0])).permitAll()
            .and()
            .authorizeRequests().antMatchers(AUTH_ENDPOINT).authenticated();

        // OAuth2ClientAuthenticationProcessingFilter 前验证request中是否包含了clientId, 之后验证请求的url用户是否有权限
        http.addFilterBefore(requestHeaderAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class);
        http.addFilterAfter(grantedAuthorityFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }

    /**
     * 验证request header中是否包含了clientId.
     *
     * @return RequestHeaderAuthenticationFilter
     */
    @Bean
    public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() {
        RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter = new RequestHeaderAuthenticationFilter();
        requestHeaderAuthenticationFilter.setPrincipalRequestHeader("clientId");
        requestHeaderAuthenticationFilter.setExceptionIfHeaderMissing(true);
        requestHeaderAuthenticationFilter.setAuthenticationManager(new RequestHeaderAuthenticationManager());
        return requestHeaderAuthenticationFilter;
    }

    private static class RequestHeaderAuthenticationManager implements AuthenticationManager {

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            ThreadLocalHolder.setClientId(authentication.getPrincipal().toString());
            return authentication;
        }

    }
}
