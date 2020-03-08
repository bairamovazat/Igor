package com.apeiron.igor.model.room;

import com.apeiron.igor.model.action.Acton;
import com.apeiron.igor.model.action.ChosePersonActon;
import com.apeiron.igor.model.event.ChosePersonEvent;
import com.apeiron.igor.model.room.annotation.EventHandler;
import com.apeiron.igor.model.room.annotation.Room;

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
