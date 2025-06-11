package com.postgresql.reverbclone.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.reverbclone.model.Users;
import com.postgresql.reverbclone.repo.UserRepo;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfileController {
    @Autowired
    private UserRepo repo;
    
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUserDetails(@PathVariable String username) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tokenUsername = authentication.getName(); // Set by your JwtFilter

        if (!tokenUsername.equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to view this profile.");
        }
        
        Users user = repo.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Map.of(
            "id", user.getId(),
            "username", user.getUsername()
        ));
    }
}
