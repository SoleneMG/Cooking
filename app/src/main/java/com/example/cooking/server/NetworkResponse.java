package com.example.cooking.server;

public class NetworkResponse<T, E> {
    public final E error;
    public final T data;

    public NetworkResponse(E error, T data) {
        this.error = error;
        this.data = data;
    }
}
