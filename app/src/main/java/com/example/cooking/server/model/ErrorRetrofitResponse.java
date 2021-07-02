package com.example.cooking.server.model;

import com.example.cooking.server.model.json.ErrorJson;
import com.google.gson.annotations.SerializedName;

public class ErrorRetrofitResponse {
    @SerializedName("error")
    public final ErrorJson errorJson;

    public ErrorRetrofitResponse(ErrorJson errorJson) {
        this.errorJson = errorJson;
    }
}
