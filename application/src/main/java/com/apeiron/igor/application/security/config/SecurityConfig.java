package com.apeiron.igor.application.security.config;

import com.apeiron.igor.api.RequestPath;
import com.apeiron.igor.application.security.filters.TokenAuthFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("tokenAuthenticationProvider")
    private AuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    private TokenAuthFilter tokenAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
                .authenticationProvider(tokenAuthenticationProvider)
                .authorizeRequests()
                .antMatchers(RequestPath.TOKEN + "/**").permitAll()
                .antMatchers(RequestPath.USER + "/**").permitAll()
                .antMatchers(RequestPath.API + "/**").authenticated();
//                .anyRequest().denyAll();
        http.csrf().disable();
    }


}
