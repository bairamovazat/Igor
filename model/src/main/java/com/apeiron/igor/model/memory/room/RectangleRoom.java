package com.apeiron.igor.model.memory.room;

import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.memory.action.ChosePerson;
import com.apeiron.igor.model.memory.event.EnemyPlayerChosePerson;
import com.apeiron.igor.model.memory.room.annotation.EventHandler;
import com.apeiron.igor.model.memory.room.annotation.Room;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Room
public class RectangleRoom extends AbstractRoom {

    public RectangleRoom(RoomCallback roomCallback) {
        super(roomCallback);
    }

    @Getter
    public List<User> users = new ArrayList<>();

    public boolean userContains(User user) {
       return users.stream().anyMatch(e -> Objects.equals(e.getId(), user.getId()));
    }

    @EventHandler(event = ChosePerson.class)
    public void processChosePerson(ChosePerson chosePerson) {
        EnemyPlayerChosePerson enemyPlayerChosePerson = EnemyPlayerChosePerson.builder()
                .build();
        callback(enemyPlayerChosePerson);
    }

}
