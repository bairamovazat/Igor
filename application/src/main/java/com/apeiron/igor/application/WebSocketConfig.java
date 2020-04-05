package com.apeiron.igor.application;

import com.apeiron.igor.application.security.principal.CustomHandshakeHandler;
import com.apeiron.igor.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final TokenRepository tokenRepository;
    private final UserDetailsService userDetailsService;

    public WebSocketConfig(TokenRepository tokenRepository, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.tokenRepository = tokenRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //Для подписки
        config.enableSimpleBroker("/topic/", "/queue/");
        //Куда шлются сообщения
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/igor-websocket")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new CustomHandshakeHandler(tokenRepository, userDetailsService))
                .withSockJS();
    }

}