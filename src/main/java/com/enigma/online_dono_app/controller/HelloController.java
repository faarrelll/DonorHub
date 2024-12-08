package com.enigma.online_dono_app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello World";
    }
}
