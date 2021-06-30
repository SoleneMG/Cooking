package com.example.cooking.server.model;

import com.example.cooking.model.User;
import com.example.cooking.server.model.NetworkResponse;

public class NetworkResponseSuccess<T> extends NetworkResponse<T> {
    public final T data;

    public NetworkResponseSuccess(final T data) {
        super(true);
        this.data = data;
    }
}
