package com.apeiron.igor.model.memory.room;

import com.apeiron.igor.model.memory.action.ChosePersonActon;
import com.apeiron.igor.model.memory.event.ChosePersonEvent;
import com.apeiron.igor.model.memory.room.annotation.EventHandler;
import com.apeiron.igor.model.memory.room.annotation.Room;

@Room
public class SimpleRoom extends AbstractRoom {

    public SimpleRoom(RoomCallback roomCallback) {
        super(roomCallback);
    }

    @EventHandler(event = ChosePersonActon.class)
    public void processChosePerson(ChosePersonActon chosePersonActon) {
        ChosePersonEvent chosePersonEvent = ChosePersonEvent.builder()
                .recipientUser(chosePersonActon.getSendingUser())
                .success(true)
                .build();
        callback(chosePersonEvent);
    }

}
