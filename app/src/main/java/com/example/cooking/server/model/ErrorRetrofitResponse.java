package com.example.cooking.server.model;

import com.google.gson.annotations.SerializedName;

public class ErrorRetrofitResponse {
    @SerializedName("error")
    public final ErrorJson errorJson;

    public ErrorRetrofitResponse(ErrorJson errorJson) {
        this.errorJson = errorJson;
    }
}
