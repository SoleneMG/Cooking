package com.example.cooking.server.serverImpl;

import com.example.cooking.server.model.LoginJson;
import com.example.cooking.server.model.UserNetworkResponseJson;
import com.example.cooking.server.model.RegisterJson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CookServer {

    @POST("api/1/register")
    Call<UserNetworkResponseJson> postRegister(@Body RegisterJson registerJson);

    @POST("api/1/login")
    Call<UserNetworkResponseJson> postLogin(@Body LoginJson loginJson);
}
