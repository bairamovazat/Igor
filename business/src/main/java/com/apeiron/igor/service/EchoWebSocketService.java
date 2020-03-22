package com.apeiron.igor.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class EchoWebSocketService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/secured/room")
    public void sendSpecific(@Payload Object msg,
            Principal user,
            @Header("simpSessionId") String sessionId) throws Exception {
        simpMessagingTemplate.convertAndSendToUser(user.getName(), "/secured/user/queue/specific-user", msg);
    }
}
