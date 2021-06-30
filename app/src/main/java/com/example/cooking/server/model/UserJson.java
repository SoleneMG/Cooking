package com.example.cooking.server.model;

public abstract class UserJson {
    public final String publicId;
    public final String id;
    public final String email;

    public UserJson(String publicId, String id, String email) {
        this.publicId = publicId;
        this.id = id;
        this.email = email;
    }
}
