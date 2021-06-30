package com.example.cooking.server.model;

public abstract class ErrorJson {
    public final int code;
    public final String status;
    public final int reasonCode;
    public final String reasonStatus;

    public ErrorJson(int code, String status, int reasonCode, String reasonStatus) {
        this.code = code;
        this.status = status;
        this.reasonCode = reasonCode;
        this.reasonStatus = reasonStatus;
    }
}
