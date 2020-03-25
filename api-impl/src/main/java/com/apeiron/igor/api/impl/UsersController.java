package com.apeiron.igor.api.impl;

import com.apeiron.igor.api.RequestPath;
import com.apeiron.igor.service.UserService;
import com.apeiron.igor.form.UserForm;
import com.apeiron.igor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(RequestPath.USER)
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public User getTestUser(){
        return User.builder()
                .name("Azat")
                .login("bairamovazat")
                .build();
    }

    @PostMapping("")
    public ResponseEntity<Object> createUser(@RequestBody UserForm userForm) {
        userService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}