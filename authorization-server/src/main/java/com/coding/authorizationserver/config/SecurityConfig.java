package com.coding.authorizationserver.config;

import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

import com.coding.authorizationserver.config.security.CaptchaAuthenticationFilter;
import com.coding.authorizationserver.config.security.InMemoryUser;
import com.coding.authorizationserver.constant.Urls;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_FORM_URL = Urls.LOGIN;
    private static final String AUTH_ENTRY_POINT = Urls.AUTH_ENTRY_POINT;
    private static final String SUCCESS_FORWARD_URL = Urls.DASHBOARD;

    private static List<String> ignoreResources = new LinkedList<>();
    private static List<String> anonymousEntryPointList = new LinkedList<>();

    static {
        ignoreResources.add("/css/**");
        ignoreResources.add("/images/**");
        ignoreResources.add("/js/**");
        ignoreResources.add("/webjars/**");

        anonymousEntryPointList.add(Urls.LOGIN);
        anonymousEntryPointList.add("/anon/**");
        anonymousEntryPointList.add("/oauth/**");
    }

    @Inject
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Inject
    private InMemoryUser inMemoryUser;

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint(LOGIN_FORM_URL);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.security.user")
    public InMemoryUser inMemoryUser() {
        return new InMemoryUser();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(ignoreResources.toArray(new String[0]));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
            .withUser(inMemoryUser.getName())
            .password(inMemoryUser.getPassword())
            .authorities((GrantedAuthority) () -> "ROLE_ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http.formLogin()
            .loginPage(LOGIN_FORM_URL).defaultSuccessUrl(SUCCESS_FORWARD_URL, true)
            .and().logout();
        http.authorizeRequests()
            .antMatchers(anonymousEntryPointList.toArray(new String[0])).permitAll()
            .and()
            .authorizeRequests().antMatchers(AUTH_ENTRY_POINT).authenticated();

        CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter();
        captchaAuthenticationFilter.setAuthenticationManager(super.authenticationManager());
        http.addFilterAt(captchaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
