package com.example.todolist.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todolist")
public class UserController {
    @GetMapping("/users")
    public ResponseEntity<String> greeting(Authentication authentication) {
        return ResponseEntity.ok("Hello " + authentication.getName());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("password");

        System.out.println(name + " " + email);
        return ResponseEntity.ok("Done");
    }
}
