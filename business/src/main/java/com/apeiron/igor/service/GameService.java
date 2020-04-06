package com.apeiron.igor.service;

import com.apeiron.igor.model.config.PrincipalImpl;
import com.apeiron.igor.model.memory.action.Attack;
import com.apeiron.igor.model.memory.action.ChosePerson;
import com.apeiron.igor.model.xap.GameInvite;

public interface GameService {
    void chosePerson(ChosePerson msg, PrincipalImpl principal, String gameId);

    void sendAttack(Attack msg, PrincipalImpl principal, String gameId);

    void startRoom(GameInvite gameInvite);

}
