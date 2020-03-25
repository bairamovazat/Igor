package com.apeiron.igor.service;

import com.apeiron.igor.dto.TokenDto;
import com.apeiron.igor.form.LoginForm;
import com.apeiron.igor.model.Token;
import com.apeiron.igor.model.User;
import com.apeiron.igor.repository.TokenRepository;
import com.apeiron.igor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<User> userCandidate = userRepository.findOneByLogin(loginForm.getLogin());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .value(UUID.randomUUID().toString())
                        .build();

                tokenRepository.save(token);
                return TokenDto.from(token);
            }
        }
        throw new IllegalArgumentException("User not found");
    }

}
