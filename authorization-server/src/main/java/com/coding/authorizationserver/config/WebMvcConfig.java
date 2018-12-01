package com.coding.authorizationserver.config;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig.
 *
 * @author caijunhui
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 验证码Bean.
     *
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean kaptchaServletBean() {
        ServletRegistrationBean<KaptchaServlet> bean = new ServletRegistrationBean<>(new KaptchaServlet(), "/anon/kaptcha.jpg");
        bean.addInitParameter("kaptcha.border", "no");
        bean.addInitParameter("kaptcha.textproducer.font.color", "red");
        bean.addInitParameter("kaptcha.textproducer.char.space", "3");
        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
