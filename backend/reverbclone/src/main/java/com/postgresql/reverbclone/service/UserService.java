package com.postgresql.reverbclone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postgresql.reverbclone.model.Users;
import com.postgresql.reverbclone.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

     public Users register(Users user) {
        return repo.save(user);
     }
}
