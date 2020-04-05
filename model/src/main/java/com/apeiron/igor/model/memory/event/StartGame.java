package com.apeiron.igor.model.memory.event;

import com.apeiron.igor.model.db.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StartGame implements Event {
    public String eventName = "StartGame";

    public User user;
}
