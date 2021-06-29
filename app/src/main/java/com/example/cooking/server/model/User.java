package com.example.cooking.server.model;

public class User {
    public final String publicId;
    public final String id;
    public final String email;

    public User(String publicId, String id, String email) {
        this.publicId = publicId;
        this.id = id;
        this.email = email;
    }
}
