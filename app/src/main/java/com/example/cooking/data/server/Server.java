package com.example.cooking.data.server;

public interface Server {

    void sendPostRegister(String email, String password, String language, MyCallback myCallback);

    void sendPostLogin(String email, String password,MyCallback myCallback);


}

