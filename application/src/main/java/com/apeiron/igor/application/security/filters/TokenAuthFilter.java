package com.apeiron.igor.application.security.filters;

import com.apeiron.igor.application.security.authentications.TokenAuthentication;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Qualifier("tokenAuthFilter")
@Component
public class TokenAuthFilter extends GenericFilterBean {

    private static String TOKEN_NAME = "token";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String token = getToken(request);
        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        tokenAuthentication.setAuthenticated(token == null);
        SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private String getToken(HttpServletRequest servletRequest) {
        String header = servletRequest.getHeader(TOKEN_NAME);
        String param = servletRequest.getParameter(TOKEN_NAME);
        return header != null ? header : param;
    }
}