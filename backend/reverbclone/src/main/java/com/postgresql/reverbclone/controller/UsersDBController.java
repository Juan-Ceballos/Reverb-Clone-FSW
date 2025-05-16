package com.postgresql.reverbclone.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.postgresql.reverbclone.model.Users;
import com.postgresql.reverbclone.repo.UserRepo;

@RestController
@RequestMapping("/users")
public class UsersDBController {

    private final UserRepo userRepo;

    public UsersDBController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        // This method should return a list of all users from the database
        // You can use the UserRepo to fetch the data
        
        return userRepo.findAll();
    }
}
