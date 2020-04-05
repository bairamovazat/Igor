package com.apeiron.igor.application.security.config;

import com.apeiron.igor.api.RequestPath;
import com.apeiron.igor.application.security.filters.TokenAuthFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private AuthenticationProvider tokenAuthenticationProvider;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Qualifier("tokenAuthFilter")
    @Autowired
    private GenericFilterBean tokenAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/**")
                .authenticated();

        http.csrf().disable();
        http.formLogin().disable();
        http.logout().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    private AuthenticationProvider authenticationProvider;


    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/signIn");
    }

}
