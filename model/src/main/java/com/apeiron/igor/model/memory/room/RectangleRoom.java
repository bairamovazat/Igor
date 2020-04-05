package com.apeiron.igor.model.memory.room;

import com.apeiron.igor.model.memory.action.ChosePerson;
import com.apeiron.igor.model.memory.event.EnemyPlayerChosePerson;
import com.apeiron.igor.model.memory.room.annotation.EventHandler;
import com.apeiron.igor.model.memory.room.annotation.Room;

@Room
public class RectangleRoom extends AbstractRoom {

    public RectangleRoom(RoomCallback roomCallback) {
        super(roomCallback);
    }

    @EventHandler(event = ChosePerson.class)
    public void processChosePerson(ChosePerson chosePerson) {
        EnemyPlayerChosePerson enemyPlayerChosePerson = EnemyPlayerChosePerson.builder()
                .build();
        callback(enemyPlayerChosePerson);
    }

}
