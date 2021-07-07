package com.example.cooking.data.server;

import com.example.cooking.data.server.MyCallback;

public interface Server {

    void sendPostRegister(String email, String password, String language, MyCallback myCallback);

    void sendPostLogin(String email, String password,MyCallback myCallback);


}

