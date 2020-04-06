package com.apeiron.igor.repository;

import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.memory.room.RectangleRoom;

import java.util.List;

public interface GameRepository {
    void save(RectangleRoom room);

    List<RectangleRoom> findRoomByUser(User user);
}
