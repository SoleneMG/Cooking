package com.example.cooking.server;

import com.example.cooking.model.Error;
import com.example.cooking.model.User;
import com.example.cooking.server.model.NetworkResponse;

public interface MyCallback {
    void onCompleteRegisterCall(NetworkResponse<Error.RegisterError, User> networkResponse);
    void onCompleteLoginCall(NetworkResponse<Error.LoginError, User> networkResponse);
}
