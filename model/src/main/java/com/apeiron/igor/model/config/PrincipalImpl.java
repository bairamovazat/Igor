package com.apeiron.igor.model.config;

import com.apeiron.igor.model.db.User;

import java.security.Principal;

public class PrincipalImpl implements Principal {
    private User user;

    public PrincipalImpl(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getLogin();
    }

    public User getUser() {
        return user;
    }
}
