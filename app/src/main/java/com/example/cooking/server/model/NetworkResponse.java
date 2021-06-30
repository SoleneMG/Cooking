package com.example.cooking.server.model;

public abstract class NetworkResponse<T> {
    public final boolean isSuccess;

    public NetworkResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}



