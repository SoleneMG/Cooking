package com.example.cooking.domain;

import com.example.cooking.server.MyCallback;
import com.example.cooking.server.Server;
import com.example.cooking.server.serverImpl.ServerImpl;

public class RegisterLogic {
    private final Server server;


    public RegisterLogic(Server server) {
        this.server = server;
    }

    public void register(String email, String password, String language, MyCallback myCallback) {

        server.sendPostRegister(email, password, language, myCallback);
    }
}
