package com.apeiron.igor.api.impl;

import com.apeiron.igor.api.RequestPath;
import com.apeiron.igor.service.UsersService;
import com.apeiron.igor.dto.UserDto;
import com.apeiron.igor.form.UserForm;
import com.apeiron.igor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RequestPath.USER)
public class UsersController {
    @Autowired
    private UsersService usersService;


    @GetMapping("/test")
    public User getTestUser(){
        return User.builder()
                .name("Azat")
                .login("bairamovazat")
                .build();
    }

    @PostMapping("")
    public ResponseEntity<Object> createUser(@RequestBody UserForm userForm) {
        usersService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}