package com.apeiron.igor.repository.impl;

import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.xap.GameInvite;
import com.apeiron.igor.repository.GameInviteRepository;
import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class GameInviteRepositoryImpl implements GameInviteRepository {

    @Autowired
    private GigaSpace gigaSpace;

    @Override
    public List<GameInvite> getAllByInitiatorAndClosed(User initiator, Boolean closed) {
        GameInvite[] results = gigaSpace.readMultiple(new SQLQuery<>(GameInvite.class, "initiator = ? AND closed = ?")
                .setParameter(1, initiator.getId())
                .setParameter(2, closed)
        );
        return Arrays.asList(results);
    }

    @Override
    public List<GameInvite> getAllByInvitedAndClosed(User invited, Boolean closed) {
        GameInvite[] results = gigaSpace.readMultiple(new SQLQuery<>(GameInvite.class, "invited = ? AND closed = ?")
                .setParameter(1, invited.getId())
                .setParameter(2, closed)
        );
        return Arrays.asList(results);
    }

    @Override
    public void create(GameInvite gameInvite) {
        gigaSpace.write(gameInvite).getObject();
    }
}
