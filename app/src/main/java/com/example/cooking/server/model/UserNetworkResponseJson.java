package com.example.cooking.server.model;

public class UserNetworkResponseJson {
    public final UserJson data;
    public final ErrorJson errorJson;

    public UserNetworkResponseJson(UserJson data, ErrorJson errorJson) {
        this.data = data;
        this.errorJson = errorJson;
    }

}
