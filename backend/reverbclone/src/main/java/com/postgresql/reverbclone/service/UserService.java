package com.postgresql.reverbclone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.postgresql.reverbclone.model.UserRegistrationRequest;
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

        // use repo to check if user exist based on username if not throws error that user exist already     
        public Users register(UserRegistrationRequest request) {
            if (repo.existsByUsername(request.getUsername())) {
                throw new RuntimeException("User with username " + request.getUsername() + " already exists");
            }
            // populate user model using the frontend request model and saves in database
            Users user = new Users();
            user.setUsername(request.getUsername());
            user.setPassword(encoder.encode(request.getPassword()));
            Users savedUser = repo.save(user);
            return savedUser;
        }

        // uses jwtservice to create jwt token for user if authenticated
        public String verify(Users user) {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            return jwtService.generateToken(user.getUsername());
        }
}
