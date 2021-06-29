package com.example.cooking.server;

import com.example.cooking.server.model.Error;

public class NetworkResponseFailure extends NetworkResponse {
    public final Error error;

    public NetworkResponseFailure(Error error) {
        this.error = error;
    }


}
