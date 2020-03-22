package com.apeiron.igor.model.room;

import com.apeiron.igor.model.event.Event;

@FunctionalInterface
public interface RoomCallback {
    void callback(Event event);
}
