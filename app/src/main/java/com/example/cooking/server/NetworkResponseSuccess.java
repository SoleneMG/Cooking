package com.example.cooking.server;

import com.example.cooking.server.model.User;

public class NetworkResponseSuccess extends NetworkResponse {
    public final User user;

    public NetworkResponseSuccess(User user) {
        this.user = user;
    }
}
