package com.apeiron.igor.api.impl;

import com.apeiron.igor.api.RequestPath;
import com.apeiron.igor.service.LoginService;
import com.apeiron.igor.dto.TokenDto;
import com.apeiron.igor.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(RequestPath.TOKEN)
public class TokenController {

    @Autowired
    private LoginService loginService;

    @PreAuthorize("permitAll")
    @GetMapping("")
    public ResponseEntity<TokenDto> login(LoginForm loginForm) {
        return ResponseEntity.ok(loginService.login(loginForm));
    }
}