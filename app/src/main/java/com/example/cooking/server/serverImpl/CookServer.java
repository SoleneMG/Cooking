package com.example.cooking.server.serverImpl;

import com.example.cooking.server.model.UserNetworkResponseJson;
import com.example.cooking.server.model.RegisterJson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CookServer {

    @POST("register")
    <T> Call<UserNetworkResponseJson> postRegister(@Body RegisterJson registerJson);
}
