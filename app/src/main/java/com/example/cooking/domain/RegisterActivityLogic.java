package com.example.cooking.domain;

import com.example.cooking.server.Server;
import com.example.cooking.server.ServerImpl;

public class RegisterActivityLogic {
    private final Server server;


    public RegisterActivityLogic(Server server) {
        this.server = server;
    }

    public void register(String email, String password, String language, ServerImpl.MyCallback myCallback) {

        server.sendPostRegister(email, password, language, myCallback);
    }
}
