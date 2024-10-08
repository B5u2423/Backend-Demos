package com.example.todolist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todolist")
public class MainController {
    @GetMapping
    public ResponseEntity<String> greeting(Authentication authentication  ) {
        return ResponseEntity.ok("Hello " + authentication.getName());
    }


}
