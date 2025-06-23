package com.postgresql.reverbclone.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.reverbclone.model.Users;
import com.postgresql.reverbclone.service.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5173")
public class LoginUserController {

    @Autowired
    private UserService service;

    // login user verify user with token return token response
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {

        try {
            String token = service.verify(user);
            return ResponseEntity.ok(Map.of("token", token));
        } catch(BadCredentialsException e) {
            return ResponseEntity.status(401)
                .body(Map.of("message", "Invalid Credentials"));
        }
    }

}
