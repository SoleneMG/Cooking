package com.example.cooking.data.server.model;

public class NetworkResponseSuccess<E, T> extends NetworkResponse<E, T> {
    public final T data;

    public NetworkResponseSuccess(final T data) {
        super(true);
        this.data = data;
    }
}
