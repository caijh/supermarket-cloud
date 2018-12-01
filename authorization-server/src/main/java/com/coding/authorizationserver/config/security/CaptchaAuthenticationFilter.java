package com.coding.authorizationserver.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coding.commons.util.AssertUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class CaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String SECURITY_FORM_CAPTCHA_KEY = "captcha";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String captcha = this.obtainCaptcha(request);
        AssertUtils.notNull(captcha, CaptchaAuthenticationException.class, "captcha is null");
        String sessionCaptcha = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        AssertUtils.eq(captcha, sessionCaptcha, CaptchaAuthenticationException.class, "captcha is incorrect");

        return super.attemptAuthentication(request, response);
    }

    private String obtainCaptcha(HttpServletRequest request) {
        return request.getParameter(SECURITY_FORM_CAPTCHA_KEY);
    }

}
