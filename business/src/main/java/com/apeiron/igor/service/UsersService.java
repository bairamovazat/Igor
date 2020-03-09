package com.apeiron.igor.service;

import com.apeiron.igor.form.UserForm;
import com.apeiron.igor.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    void signUp(UserForm userForm);

    List<User> findAll();

    User findOne(Long userId);
}
