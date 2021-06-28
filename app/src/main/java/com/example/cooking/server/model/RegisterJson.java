package com.example.cooking.server.model;

public class RegisterJson {
    public final String email, password, language;

    public RegisterJson(String email, String password, String language) {
        this.email = email;
        this.password = password;
        this.language = language;
    }


}
