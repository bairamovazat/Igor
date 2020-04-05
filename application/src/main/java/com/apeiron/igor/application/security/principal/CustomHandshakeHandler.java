package com.apeiron.igor.application.security.principal;

import com.apeiron.igor.model.config.PrincipalImpl;
import com.apeiron.igor.model.db.Token;
import com.apeiron.igor.repository.TokenRepository;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    private TokenRepository tokenRepository;
    private UserDetailsService userDetailsService;

    public CustomHandshakeHandler(TokenRepository tokenRepository,
                                  UserDetailsService userDetailsService) {
        this.tokenRepository = tokenRepository;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {

        Map<String, String[]> tokenList = ((ServletServerHttpRequest) request).getServletRequest().getParameterMap();
        if (tokenList == null || !tokenList.containsKey("token") ||
                tokenList.get("token") == null || tokenList.get("token").length == 0) {
            throw new SessionAuthenticationException("Token not found");
        }
        String tokenValue = tokenList.get("token")[0];
        Token token = tokenRepository.findOneByValue(tokenValue)
                .orElseThrow(() -> new BadCredentialsException("Token with value " + tokenValue + " not found in repository"));

        return new PrincipalImpl(token.getUser());
    }

}
