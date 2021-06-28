package com.example.cooking.domain;

import com.example.cooking.server.Server;

public class MainActivityLogic {
    private final Server server;


    public MainActivityLogic(Server server) {
        this.server = server;
    }

    public void connexion(String email, String password, String language) {

        server.sendPost(email, password, language);
    }
}
