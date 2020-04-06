package com.apeiron.igor.model;

import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.memory.NewGame;
import com.apeiron.igor.model.memory.room.RectangleRoom;

public interface GameFactory {
    NewGame createGame(RectangleRoom rectangleRoom, User enemyPlayer);
}
