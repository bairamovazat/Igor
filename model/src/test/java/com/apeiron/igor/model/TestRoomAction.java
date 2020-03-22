package com.apeiron.igor.model;

import com.apeiron.igor.model.action.ChosePersonActon;
import com.apeiron.igor.model.event.ChosePersonEvent;
import com.apeiron.igor.model.event.Event;
import com.apeiron.igor.model.room.Room;
import com.apeiron.igor.model.room.SimpleRoom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class TestRoomAction {

    private Room room;

    @Before
    public void init() {

    }

    @Test
    public void testChosePersonAction() {
        Queue<Event> eventQueue = new ArrayDeque<>();

        Room room = new SimpleRoom(eventQueue::add);
        User user = User.builder()
                .id(1L)
                .name("Test name")
                .build();
        Person person = Person.builder()
                .personName("Test person name")
                .id(1L)
                .build();

        ChosePersonActon chosePersonActon = ChosePersonActon.builder()
                .sendingUser(user)
                .selectedPerson(person)
                .build();

        room.processActon(chosePersonActon);
        Event event = eventQueue.peek();

        Assert.assertNotNull(event);
        Assert.assertEquals(event.getClass(), ChosePersonEvent.class);
        Assert.assertTrue(((ChosePersonEvent) event).getSuccess());
        Assert.assertEquals(event.getRecipientUser(), chosePersonActon.getSendingUser());
    }
}
