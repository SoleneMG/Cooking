package com.example.cooking.data.server.model.json;

public class UserJson {
    public final String publicId;
    public final String id;
    public final String email;

    public UserJson(String publicId, String id, String email) {
        this.publicId = publicId;
        this.id = id;
        this.email = email;
    }
}
