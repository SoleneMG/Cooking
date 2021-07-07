package com.example.cooking.data.server.model;

public abstract class NetworkResponse<E, T> {
    public final boolean isSuccess;

    public NetworkResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}



