package com.example.cooking.server.model;

import com.google.gson.annotations.SerializedName;

public class SuccesRetrofitResponse {
    @SerializedName("success")
    public final UserJson userJson;

    public SuccesRetrofitResponse(UserJson userJson) {
        this.userJson = userJson;
    }
}
