package com.example.cooking.domain;

import com.example.cooking.server.Server;

public class LoginActivityLogic {
    private final Server server;


    public LoginActivityLogic(Server server) {
        this.server = server;
    }

    public void login(String email, String password) {

      //  server.sendPostLogin(email, password);
    }
}
