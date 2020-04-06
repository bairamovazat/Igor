package com.apeiron.igor.api.impl;

import com.apeiron.igor.api.RequestPath;
import com.apeiron.igor.model.config.PrincipalImpl;
import com.apeiron.igor.model.xap.GameInvite;
import com.apeiron.igor.repository.GameInviteRepository;
import com.apeiron.igor.service.GameSearchService;
import com.apeiron.igor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(RequestPath.GAME_SEARCH)
public class GameSearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private GameSearchService gameSearchService;

    @Autowired
    private GameInviteRepository gameInviteRepository;

    @GetMapping("/incoming")
    public List<GameInvite> getIncomingInvites() {
        return gameInviteRepository.getAllByInvitedAndClosed(userService.getCurrentUser(), false);
    }

    @GetMapping("/outgoing")
    public List<GameInvite> getOutgoingInvites() {
        return gameInviteRepository.getAllByInitiatorAndClosed(userService.getCurrentUser(), false);
    }

    @MessageMapping("/invite/create")
    public void createInvite(@Payload GameInvite msg, Principal principal) throws Exception {
        gameSearchService.createInvite(msg, (PrincipalImpl) principal);
    }

    @MessageMapping("/invite/apply")
    public void applyInvite(@Payload GameInvite msg, Principal principal) throws Exception {
        gameSearchService.acceptInvite(msg, (PrincipalImpl) principal);
    }
}
