package com.example.sparewise;

public class User {
    private String username;
    private String email;
    private String password;

    public User() {
        // Default constructor required for Firebase
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    // ...
}
