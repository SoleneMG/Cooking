package com.example.cooking.domain;

import com.example.cooking.data.server.MyCallback;
import com.example.cooking.data.server.Server;

public class RegisterLogic {
    private final Server server;

    public RegisterLogic(Server server) {
        this.server = server;
    }

    public void register(String email, String password, String language, MyCallback myCallback) {

        server.sendPostRegister(email, password, language, myCallback);
    }
}
