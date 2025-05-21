package com.postgresql.reverbclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.reverbclone.model.Users;
import com.postgresql.reverbclone.service.UserService;

@RestController
public class LoginUserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return service.verify(user);
    }
}
