package com.apeiron.igor.repository;

import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.xap.GameInvite;

import java.util.List;

public interface GameInviteRepository {
    List<GameInvite> getAllByInitiatorAndClosed(User initiator, Boolean closed);

    List<GameInvite> getAllByInvitedAndClosed(User invited, Boolean closed);

    void create(GameInvite gameInvite);
}
