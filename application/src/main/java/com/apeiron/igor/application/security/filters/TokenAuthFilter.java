package com.apeiron.igor.application.security.filters;

import com.apeiron.igor.application.security.authentications.TokenAuthentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
public class TokenAuthFilter implements Filter {

    private static String TOKEN_NAME = "token";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String token = getToken(request);

        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        if (token == null) {
            tokenAuthentication.setAuthenticated(false);
        } else {
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private String getToken(HttpServletRequest servletRequest) {
        if(servletRequest.getCookies() == null) {
            return null;
        }
        return Arrays.stream(servletRequest.getCookies())
                .filter(cookie -> Objects.equals(cookie.getName(), TOKEN_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }
}