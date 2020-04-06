package com.apeiron.igor.api.impl;

import com.apeiron.igor.api.RequestPath;
import com.apeiron.igor.enums.GameUrl;
import com.apeiron.igor.model.config.PrincipalImpl;
import com.apeiron.igor.model.memory.action.Attack;
import com.apeiron.igor.model.memory.action.ChosePerson;
import com.apeiron.igor.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping(RequestPath.GAME)
public class GameController {

    @Autowired
    private GameService gameService;

    @MessageMapping(GameUrl.sendChosePerson + "/{gameId}")
    public void chosePerson(@Payload ChosePerson msg, Principal principal, @PathVariable String gameId) throws Exception {
        gameService.chosePerson(msg, (PrincipalImpl)principal, gameId);
    }

    @MessageMapping(GameUrl.sendAttack + "/{gameId}")
    public void sendAttack(@Payload Attack msg, Principal principal, @PathVariable String gameId) throws Exception {
        gameService.sendAttack(msg, (PrincipalImpl)principal, gameId);

    }

}
