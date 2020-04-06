package com.apeiron.igor.service;

import com.apeiron.igor.model.config.PrincipalImpl;
import com.apeiron.igor.model.xap.GameInvite;

import java.security.Principal;

public interface GameSearchService {
    void createInvite(GameInvite gameInvite, PrincipalImpl webSocketPrincipal);

    void acceptInvite(GameInvite msg, PrincipalImpl webSocketPrincipal);
}
