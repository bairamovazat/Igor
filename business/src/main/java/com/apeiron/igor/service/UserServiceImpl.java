package com.apeiron.igor.service;


import com.apeiron.igor.form.UserForm;
import com.apeiron.igor.model.db.Role;
import com.apeiron.igor.model.db.State;
import com.apeiron.igor.model.db.Token;
import com.apeiron.igor.model.db.User;
import com.apeiron.igor.repository.TokenRepository;
import com.apeiron.igor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void signUp(UserForm userForm) {
        if (userRepository.findOneByLogin(userForm.getLogin()).isPresent()) {
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

    @Override
    public User getCurrentUser() {
        return tokenRepository.findOneByValue(getCurrentTokenName())
                .map(Token::getUser)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
    }

    @Override
    public String getCurrentTokenName() {
        return (SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public String getCurrentUserName(Principal principal) {
        return Optional.ofNullable(principal)
                .orElseThrow(() -> new NullPointerException("Principal null"))
                .getName();
    }
}
