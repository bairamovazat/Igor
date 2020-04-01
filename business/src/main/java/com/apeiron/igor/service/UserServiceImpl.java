package com.apeiron.igor.service;


import com.apeiron.igor.form.UserForm;
import com.apeiron.igor.model.Role;
import com.apeiron.igor.model.State;
import com.apeiron.igor.model.User;
import com.apeiron.igor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserForm userForm) {
        if (userRepository.findOneByLogin(userForm.getLogin()).isPresent()){
            throw new IllegalArgumentException("Логин уже занят");
        }

        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .name(userForm.getName())
                .hashPassword(hashPassword)
                .login(userForm.getLogin())
                .role(Role.USER)
                .state(State.ACTIVATED)
                .build();

        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long userId) {
        return userRepository.findOneById(userId).orElse(null);
    }
}
