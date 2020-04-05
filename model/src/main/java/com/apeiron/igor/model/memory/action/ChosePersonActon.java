package com.apeiron.igor.model.memory.action;

import com.apeiron.igor.model.db.Person;
import com.apeiron.igor.model.db.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChosePersonActon implements Acton {
    private User sendingUser;

    private Person selectedPerson;

    @Override
    public User getSendingUser() {
        return sendingUser;
    }
}
