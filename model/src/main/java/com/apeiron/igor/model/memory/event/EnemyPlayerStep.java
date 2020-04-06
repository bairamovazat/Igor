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
public class EnemyPlayerStep implements Event {
    @Builder.Default
    public String eventName = "EnemyPlayerStep";

    public User user;
}
