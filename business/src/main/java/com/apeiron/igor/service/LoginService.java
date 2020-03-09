package com.apeiron.igor.service;

import com.apeiron.igor.dto.TokenDto;
import com.apeiron.igor.form.LoginForm;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
