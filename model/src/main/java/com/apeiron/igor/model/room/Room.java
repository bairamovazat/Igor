package com.apeiron.igor.model.room;

import com.apeiron.igor.model.action.Acton;
import com.apeiron.igor.model.event.Event;

public interface Room {
    <T extends Acton> void processActon(T action);
}
