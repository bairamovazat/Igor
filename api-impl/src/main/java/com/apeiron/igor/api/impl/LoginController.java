package com.apeiron.igor.api.impl;

import com.apeiron.igor.service.LoginService;
import com.apeiron.igor.dto.TokenDto;
import com.apeiron.igor.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm) {
        int i = 0;
        return ResponseEntity.ok(loginService.login(loginForm));
    }
}