package com.example.cooking.data.server.model.register;

public class RegisterJson {
    public final String email, password, language;

    public RegisterJson(String email, String password, String language) {
        this.email = email;
        this.password = password;
        this.language = language;
    }


}
