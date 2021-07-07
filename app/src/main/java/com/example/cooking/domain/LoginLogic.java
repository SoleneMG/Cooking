package com.example.cooking.domain;

import com.example.cooking.data.server.MyCallback;
import com.example.cooking.data.server.Server;

public class LoginLogic {
    private final Server server;


    public LoginLogic(Server server) {
        this.server = server;
    }

    public void login(String email, String password, MyCallback myCallback) {

     server.sendPostLogin(email, password, myCallback);
    }
}
