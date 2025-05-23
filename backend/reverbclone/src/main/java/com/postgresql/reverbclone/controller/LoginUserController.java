package com.postgresql.reverbclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        System.out.println("Success! Juan!");
        return service.verify(user);
    }

//     @PostMapping("/logout")
//     public String logout() {
        
//     }
}
