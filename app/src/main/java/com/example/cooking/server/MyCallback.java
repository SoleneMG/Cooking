package com.example.cooking.server;

import com.example.cooking.server.model.NetworkResponse;

public interface MyCallback {
    void onComplete(NetworkResponse networkResponse);
}
