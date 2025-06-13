package com.postgresql.reverbclone.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// stores data in instances of this class that user
// entered in front-end and sent as request
public class UserRegistrationRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 6, message = "Username must be at least 6 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")

    private String password;

    public UserRegistrationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
