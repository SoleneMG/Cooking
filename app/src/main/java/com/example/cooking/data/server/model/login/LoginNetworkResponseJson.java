package com.example.cooking.data.server.model.login;

import com.example.cooking.data.server.model.json.ErrorJson;
import com.example.cooking.data.server.model.json.UserJson;

public class LoginNetworkResponseJson {
    public final UserJson data;
    public final ErrorJson errorJson;
    public final String refreshToken;
    public final String accessToken;

    public LoginNetworkResponseJson(UserJson data, ErrorJson errorJson, String refreshToken, String accessToken) {
        this.data = data;
        this.errorJson = errorJson;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
