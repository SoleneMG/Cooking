package com.example.cooking.data.server.model;

import com.example.cooking.model.Error;

public class NetworkResponseFailure<E, T> extends NetworkResponse<E, T> {
    public final Error<E> eError;


    public NetworkResponseFailure(final Error<E> eError) {
        super(false);
        this.eError = eError;
    }
}
