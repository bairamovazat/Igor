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
public class YourStep implements Event {
    @Builder.Default
    public String eventName = "YourStep";

    public Long personId;
    public User user;
}
