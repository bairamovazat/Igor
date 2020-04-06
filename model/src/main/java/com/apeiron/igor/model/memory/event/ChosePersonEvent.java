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
public class ChosePersonEvent implements Event {
    private Boolean success;

    private User recipientUser;

    @Override
    public User getRecipientUser() {
        return recipientUser;
    }
}
