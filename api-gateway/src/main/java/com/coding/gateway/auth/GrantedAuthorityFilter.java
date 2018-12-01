package com.coding.gateway.auth;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.resource.model.Resource;
import com.coding.commons.util.CollectionUtils;
import com.coding.gateway.constants.Urls;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Named
public class GrantedAuthorityFilter extends OncePerRequestFilter {

    private static final List<String> ANONYMOUS_ENDPOINTS = Urls.getAnonymousEndpoints();

    @Inject
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if (!ANONYMOUS_ENDPOINTS.contains(servletPath)) {
            List<String> roles = redisUtils.getListCache(Resource.GRANTED_URL_PREFIX + servletPath, String.class);

            if (CollectionUtils.isNotEmpty(roles)) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                boolean granted = authorities.stream().anyMatch(authority -> roles.contains(authority.getAuthority()));

                if (!granted) {
                    throw new ServletException("no granted authority");
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
