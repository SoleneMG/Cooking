package com.example.cooking.server.model;

import com.example.cooking.model.Error;

public class NetworkResponseFailure<T> extends NetworkResponse<T> {
    public final Error error;


    public NetworkResponseFailure(final Error error) {
        super(false);
        this.error = error;
    }
}
