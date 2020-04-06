package com.apeiron.igor.api.impl;

import com.apeiron.igor.api.RequestPath;
import com.apeiron.igor.model.config.UserDetailsImpl;
import com.apeiron.igor.model.db.Token;
import com.apeiron.igor.repository.TokenRepository;
import com.apeiron.igor.service.UserService;
import com.apeiron.igor.form.UserForm;
import com.apeiron.igor.model.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping(RequestPath.USER)
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenRepository tokenRepository;

    @PreAuthorize("permitAll")
    @GetMapping("/test")
    public User getTestUser(){
        return User.builder()
                .name("Azat")
                .login("bairamovazat")
                .build();
    }

    @GetMapping("/current")
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

    @PreAuthorize("permitAll")
    @PostMapping("")
    public ResponseEntity<Object> createUser(@RequestBody UserForm userForm) {
        userService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}