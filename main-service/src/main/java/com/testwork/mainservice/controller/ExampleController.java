package com.testwork.mainservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
