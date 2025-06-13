package com.postgresql.reverbclone.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postgresql.reverbclone.model.Users;

// Repo to access database and fetch data of a user based on username, check if username exists
@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    boolean existsByUsername(String username);
    Users findByUsername(String username);
}
