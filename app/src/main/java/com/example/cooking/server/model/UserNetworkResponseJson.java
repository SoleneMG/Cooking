package com.example.cooking.server.model;

import com.example.cooking.model.User;

public class UserNetworkResponseJson {
    public final User user;
    public final ErrorJson error;

    public UserNetworkResponseJson(User user, ErrorJson error) {
        this.user = user;
        this.error = error;
    }

}
