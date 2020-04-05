package com.apeiron.igor.service;

import com.apeiron.igor.model.config.PrincipalImpl;
import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.xap.GameInvite;
import com.apeiron.igor.repository.GameInviteRepository;
import com.apeiron.igor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class GameSearchServiceImpl implements GameSearchService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameInviteRepository gameInviteRepository;

    @Override
    public void createInvite(GameInvite gameInvite, PrincipalImpl webSocketPrincipal) {
        User invitedUser = userRepository.findOneById(gameInvite.getInvited())
                .orElseThrow(() -> new IllegalArgumentException("Приглашаемый пользователь не найден"));

        User initiatorUser = userRepository.findOneById(webSocketPrincipal.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Приглашающий пользователь не найден"));

        gameInvite.setClosed(false);
        gameInvite.setInitiator(initiatorUser.getId());
        gameInvite.setInvitedUserAccept(null);

        gameInviteRepository.create(gameInvite);
        simpMessagingTemplate.convertAndSendToUser(invitedUser.getLogin(), "/queue/invite/incoming", gameInvite);

    }
}
