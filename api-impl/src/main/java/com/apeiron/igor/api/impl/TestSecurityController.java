package com.apeiron.igor.api.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apeiron.igor.api.RequestPath;

@RestController
@RequestMapping(value = RequestPath.API)
public class TestSecurityController {

    @GetMapping
    private String testSecurity() {
        return "work";
    }
}
