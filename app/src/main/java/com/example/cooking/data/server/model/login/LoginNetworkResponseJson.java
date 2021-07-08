package com.example.cooking.data.server.model.login;

import com.example.cooking.data.server.model.json.ErrorJson;
import com.example.cooking.data.server.model.json.UserJson;

public class LoginNetworkResponseJson {
    public final LoginJson data;
    public final ErrorJson errorJson;

    public LoginNetworkResponseJson(LoginJson data, ErrorJson errorJson) {
        this.data = data;
        this.errorJson = errorJson;
    }
}
