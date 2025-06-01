package com.postgresql.reverbclone.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postgresql.reverbclone.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    boolean existsByUsername(String username);
    Users findByUsername(String username);
}
