package com.apeiron.igor.model.action;

import com.apeiron.igor.model.Person;
import com.apeiron.igor.model.User;
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
