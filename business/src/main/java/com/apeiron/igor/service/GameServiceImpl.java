package com.apeiron.igor.service;

import com.apeiron.igor.model.config.PrincipalImpl;
import com.apeiron.igor.model.memory.action.Attack;
import com.apeiron.igor.model.memory.action.ChosePerson;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {


    @Override
    public void chosePerson(ChosePerson msg, PrincipalImpl principal, String gameId) {

    }

    @Override
    public void sendAttack(Attack msg, PrincipalImpl principal, String gameId) {

    }
}
