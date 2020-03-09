package com.apeiron.igor.api.impl;

import com.apeiron.igor.service.UsersService;
import com.apeiron.igor.dto.UserDto;
import com.apeiron.igor.form.UserForm;
import com.apeiron.igor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return UserDto.from(usersService.findAll());
    }

    @GetMapping("/auth")
    public Authentication getAuthentication(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/users/{user-id}")
    public User getUser(@PathVariable("user-id") Long userId) {
        return usersService.findOne(userId);
    }

    @GetMapping("/test")
    public User getTestUser(){
        return User.builder()
                .name("Azat")
                .login("bairamovazat")
                .build();
    }
    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        usersService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}