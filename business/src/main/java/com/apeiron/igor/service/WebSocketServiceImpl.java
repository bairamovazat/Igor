package com.apeiron.igor.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {
        System.out.println(event);
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        System.out.println(event);
    }
}
