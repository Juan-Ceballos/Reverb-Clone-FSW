package com.postgresql.reverbclone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.postgresql.reverbclone.model.Users;
import com.postgresql.reverbclone.repo.UserRepo;
import com.postgresql.reverbclone.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UsersDBController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);
    }

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

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
