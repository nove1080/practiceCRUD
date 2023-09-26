package com.example.demo.domain.model;

public enum UserRole {
    USER("USER");
    final private String role;
    UserRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
