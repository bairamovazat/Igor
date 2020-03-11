package com.apeiron.igor.api.impl;

import com.apeiron.igor.service.LoginService;
import com.apeiron.igor.dto.TokenDto;
import com.apeiron.igor.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private LoginService loginService;

    @GetMapping("")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm) {
        int i = 0;
        return ResponseEntity.ok(loginService.login(loginForm));
    }
}