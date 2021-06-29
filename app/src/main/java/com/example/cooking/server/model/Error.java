package com.example.cooking.server.model;

public class Error {
    public final int code;
    public final String status;
    public final int reasonCode;
    public final String reasonStatus;

    public Error(int code, String status, int reasonCode, String reasonStatus) {
        this.code = code;
        this.status = status;
        this.reasonCode = reasonCode;
        this.reasonStatus = reasonStatus;
    }
}
