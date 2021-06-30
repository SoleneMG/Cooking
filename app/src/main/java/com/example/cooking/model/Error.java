package com.example.cooking.model;

public class Error<E> {
    public final E error;

    public Error(E error) {
        this.error = error;
    }

    public enum RegisterError {
        INVALID_EMAIL,
        INVALID_PASSWORD,
        INVALID_LANGUAGE,
        USER_ALREADY_EXIST
    }

    public enum NetworkError {
        NETWORK_ERROR
    }
}
