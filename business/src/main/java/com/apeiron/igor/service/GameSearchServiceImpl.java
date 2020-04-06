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

    @Autowired
    private GameService gameService;

    @Override
    public void createInvite(GameInvite gameInvite, PrincipalImpl webSocketPrincipal) {
        User invitedUser;
        if (gameInvite.getInvited() != null) {
            invitedUser = userRepository.findOneById(gameInvite.getInvited())
                    .orElseThrow(() -> new IllegalArgumentException("Приглашаемый пользователь не найден"));

        } else if (gameInvite.getInvitedLogin() != null) {
            invitedUser = userRepository.findOneByLogin(gameInvite.getInvitedLogin())
                    .orElseThrow(() -> new IllegalArgumentException("Приглашаемый пользователь не найден"));
        } else {
            throw new IllegalArgumentException("Приглашение пустое");
        }

        User initiatorUser = userRepository.findOneById(webSocketPrincipal.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Приглашающий пользователь не найден"));

        if (initiatorUser.getId().equals(invitedUser.getId())) {
            throw new IllegalArgumentException("Нельзя пригласить самого себя");
        }

        gameInvite.setClosed(false);
        gameInvite.setInitiator(initiatorUser.getId());
        gameInvite.setInitiatorLogin(initiatorUser.getLogin());
        gameInvite.setInvited(invitedUser.getId());
        gameInvite.setInitiatorLogin(invitedUser.getLogin());

        gameInvite.setInvitedUserAccept(null);

        gameInviteRepository.create(gameInvite);
        simpMessagingTemplate.convertAndSendToUser(invitedUser.getLogin(), "/queue/invite/incoming", gameInvite);
    }

    @Override
    public void acceptInvite(GameInvite gameInvite, PrincipalImpl principal) {

        if (gameInvite.getInvited() == null ||
                !gameInvite.getInvited().equals(principal.getUser().getId()) ||
                gameInvite.getInitiator() == null) {
            throw new IllegalArgumentException("Некорретные данные пользователей");
        }

        gameService.startRoom(gameInvite);

    }
}
