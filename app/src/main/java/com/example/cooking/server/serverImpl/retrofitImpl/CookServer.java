package com.example.cooking.server.serverImpl.retrofitImpl;

import com.example.cooking.server.model.login.LoginJson;
import com.example.cooking.server.model.login.LoginNetworkResponseJson;
import com.example.cooking.server.model.register.RegisterNetworkResponseJson;
import com.example.cooking.server.model.register.RegisterJson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CookServer {

    @POST("api/1/register")
    Call<RegisterNetworkResponseJson> postRegister(@Body RegisterJson registerJson);

    @POST("api/1/login")
    Call<LoginNetworkResponseJson> postLogin(@Body LoginJson loginJson);
}
