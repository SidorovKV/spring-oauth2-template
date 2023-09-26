package com.testwork.mainservice.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ExampleController {
    @GetMapping("/admin")
    public String admin() {
        return "Yeah! It's admin endpoint";
    }

    @GetMapping("/user")
    public String user() {
        return "Yeah! It's user endpoint";
    }

    @GetMapping("/authed")
    public String any() {
        return "Yeah! It's endpoint for anyone authed";
    }

    @GetMapping("/jwt/read")
    public Map<String, Object> read(@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> result = new HashMap<>(2);
        result.put("principal", jwt.getClaims());
        return result;
    }
}
