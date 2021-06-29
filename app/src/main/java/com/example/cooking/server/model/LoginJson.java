package com.example.cooking.server.model;

public class LoginJson {
    public final String email, password;

    public LoginJson(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
