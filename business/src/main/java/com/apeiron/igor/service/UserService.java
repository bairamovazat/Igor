package com.apeiron.igor.service;

import com.apeiron.igor.form.UserForm;
import com.apeiron.igor.model.db.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface UserService {
    void signUp(UserForm userForm);

    List<User> findAll();

    User findOne(Long userId);

    User getCurrentUser();

    String getCurrentTokenName();

    String getCurrentUserName(Principal principal);
}
