package com.example.cooking.data.server.model.register;

import com.example.cooking.data.server.model.json.ErrorJson;
import com.example.cooking.data.server.model.json.UserJson;

public class RegisterNetworkResponseJson {
    public final UserJson data;
    public final ErrorJson errorJson;

    public RegisterNetworkResponseJson(UserJson data, ErrorJson errorJson) {
        this.data = data;
        this.errorJson = errorJson;
    }

}
