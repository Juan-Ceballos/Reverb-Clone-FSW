package com.postgresql.reverbclone.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.reverbclone.model.UserRegistrationRequest;
import com.postgresql.reverbclone.model.Users;
import com.postgresql.reverbclone.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5173")
public class AddUserController {

    @Autowired
    private UserService service;
    
    // UserService register user, return response
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationRequest request) {
        try {
            Users user = service.register(request);
            return ResponseEntity.ok(user);
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "An unexpected error occured"));
        }
    }
}
