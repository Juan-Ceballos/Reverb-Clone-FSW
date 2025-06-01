package com.postgresql.reverbclone.model;

import java.time.LocalDateTime;

public class UserResponse {
    private int id;
    private String username;
    private LocalDateTime createdAt;

    public UserResponse(Users user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
}
