package com.apeiron.igor.repository.impl;

import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.memory.room.AbstractRoom;
import com.apeiron.igor.model.memory.room.RectangleRoom;
import com.apeiron.igor.model.memory.room.Room;
import com.apeiron.igor.repository.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class GameRepositoryImpl implements GameRepository {

    private Map<String, RectangleRoom> memoryStorage = new HashMap<>();

    @Override
    public void save(RectangleRoom room) {
        if(room.getId() == null) {
            room.setId(UUID.randomUUID().toString());
        }
        memoryStorage.put(room.getId(), room);
    }

    @Override
    public List<RectangleRoom> findRoomByUser(User user) {
        return memoryStorage.values().stream()
                .filter(e -> e.userContains(user))
                .collect(Collectors.toList());
    }
}
