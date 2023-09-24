package com.testwork.authservice.controller;

import com.testwork.authservice.user.InMemoryUserCredentialService;
import com.testwork.authservice.user.UserCredentialDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final InMemoryUserCredentialService inMemoryUserCredentialService;

    public AuthController(
            InMemoryUserCredentialService inMemoryUserCredentialService
    ) {
        this.inMemoryUserCredentialService = inMemoryUserCredentialService;
    }

    @PostMapping("/registration")
    public String add(@Valid @RequestBody UserCredentialDto newUserCredentials) {
        if (!newUserCredentials.getPassword().equals(newUserCredentials.getMatchPassword())) {
            throw new IllegalArgumentException("Password and match password not match");
        }
        inMemoryUserCredentialService.add(newUserCredentials);
        return "User with email " + newUserCredentials.getEmail() + " created";
    }

    @GetMapping("/authorized")
    public String au() {
        return "authorized";
    }
}
