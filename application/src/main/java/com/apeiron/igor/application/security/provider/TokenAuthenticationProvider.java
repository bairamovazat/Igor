package com.apeiron.igor.application.security.provider;

import com.apeiron.igor.application.security.authentications.TokenAuthentication;
import com.apeiron.igor.model.db.Token;
import com.apeiron.igor.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenRepository tokenRepository;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    public TokenAuthenticationProvider(){
        super();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Optional<Token> tokenCandidate = tokenRepository.findOneByValue(authentication.getName());

        if (tokenCandidate.isPresent()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(tokenCandidate.get().getUser().getLogin());
            authentication.setAuthenticated(true);
            ((TokenAuthentication)authentication).setDetails(userDetails);
            return authentication;
        } else throw new BadCredentialsException("Bad token");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}