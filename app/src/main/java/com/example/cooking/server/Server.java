package com.example.cooking.server;

public interface Server {

    void sendPostRegister(String email, String password, String language, MyCallback myCallback);


}

