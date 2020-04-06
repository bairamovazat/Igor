package com.apeiron.igor.model.memory.room;

import com.apeiron.igor.model.memory.action.Acton;

public interface Room {
    String getId();
    <T extends Acton> void processActon(T action);
}
