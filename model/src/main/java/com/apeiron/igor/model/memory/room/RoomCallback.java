package com.apeiron.igor.model.memory.room;

import com.apeiron.igor.model.memory.event.Event;

@FunctionalInterface
public interface RoomCallback {
    void callback(Event event);
}
