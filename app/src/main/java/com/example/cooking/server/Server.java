package com.example.cooking.server;

import com.example.cooking.server.model.Error;
import com.example.cooking.server.model.User;

public interface Server {

    void sendPostRegister(String email, String password, String language, ServerImpl.MyCallback myCallback);

    //void sendPostLogin(String email, String password, );
}

