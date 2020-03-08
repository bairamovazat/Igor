package com.apeiron.igor.model.event;

import com.apeiron.igor.model.User;

public interface Event {
    User getRecipientUser();
}
