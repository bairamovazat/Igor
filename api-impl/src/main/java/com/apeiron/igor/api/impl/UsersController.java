package com.apeiron.igor.api.impl;

import com.apeiron.igor.api.RequestPath;
import com.apeiron.igor.model.Token;
import com.apeiron.igor.repository.TokenRepository;
import com.apeiron.igor.service.UserService;
import com.apeiron.igor.form.UserForm;
import com.apeiron.igor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(RequestPath.USER)
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping("/test")
    public User getTestUser(){
        return User.builder()
                .name("Azat")
                .login("bairamovazat")
                .build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current")
    public User getCurrentUser(){
        return tokenRepository.findOneByValue(SecurityContextHolder.getContext().getAuthentication().getName())
                .map(Token::getUser)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
    }

    @PostMapping("")
    public ResponseEntity<Object> createUser(@RequestBody UserForm userForm) {
        userService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}