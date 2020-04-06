package com.apeiron.igor.model.memory.event;

import com.apeiron.igor.model.db.User;

public interface Event {
    User getRecipientUser();
}
