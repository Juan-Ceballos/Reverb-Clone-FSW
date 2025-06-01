package com.postgresql.reverbclone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.postgresql.reverbclone.model.UserRegistrationRequest;
import com.postgresql.reverbclone.model.UserResponse;
import com.postgresql.reverbclone.model.Users;
import com.postgresql.reverbclone.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

     public UserResponse register(UserRegistrationRequest request) {
         if (repo.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User with username " + request.getUsername() + " already exists");
         }

         //Users currUser = new Users();
         //currUser.setUsername(user.getUsername());
         Users user = new Users();
         user.setId(user.getId());
         user.setUsername(user.getUsername());
         user.setPassword(encoder.encode(user.getPassword()));
         Users savedUser = repo.save(user);
         return new UserResponse(savedUser);
     }

     public String verify(Users user) {
         authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
         return jwtService.generateToken(user.getUsername());
     }
}
