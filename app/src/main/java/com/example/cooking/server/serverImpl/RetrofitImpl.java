package com.example.cooking.server.serverImpl;

import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import com.example.cooking.model.Error;
import com.example.cooking.model.User;
import com.example.cooking.server.MyCallback;
import com.example.cooking.server.Server;
import com.example.cooking.server.model.NetworkResponse;
import com.example.cooking.server.model.NetworkResponseFailure;
import com.example.cooking.server.model.NetworkResponseSuccess;
import com.example.cooking.server.model.RegisterJson;
import com.example.cooking.server.model.UserNetworkResponseJson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImpl implements Server {
    public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(1);
    public final retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder().baseUrl("http://192.168.1.17:8080/api/1/").addConverterFactory(GsonConverterFactory.create()).build();
    public final CookServer cookServer = retrofit.create(CookServer.class);
    private final Handler myHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    @Override
    public void sendPostRegister(String email, String password, String language, MyCallback myCallback) {
        EXECUTOR.submit(() -> {
            Call<UserNetworkResponseJson> call = cookServer.postRegister(new RegisterJson(email, password, language));
            call.enqueue(new Callback<UserNetworkResponseJson>() {
                @Override
                public void onResponse(Call<UserNetworkResponseJson> call, Response<UserNetworkResponseJson> response) {
                    if (response.isSuccessful()) {
                        handleResponse(new NetworkResponseSuccess<>(new User(response.body().user.publicId, response.body().user.id, response.body().user.email)), myCallback);
                    } else {
                        switch (response.body().error.reasonCode) {
                            case 1:
                                handleResponse(new NetworkResponseFailure(new Error<>(Error.RegisterError.INVALID_EMAIL)), myCallback);
                                break;
                            case 2:
                                handleResponse(new NetworkResponseFailure(new Error<>(Error.RegisterError.INVALID_PASSWORD)), myCallback);
                                break;
                            case 3:
                                handleResponse(new NetworkResponseFailure(new Error<>(Error.RegisterError.INVALID_LANGUAGE)), myCallback);
                                break;
                            case 4:
                                handleResponse(new NetworkResponseFailure(new Error<>(Error.RegisterError.USER_ALREADY_EXIST)), myCallback);
                                break;
                            default:
                                throw new IllegalArgumentException("Error not supported" + response.body().error.reasonCode);
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserNetworkResponseJson> call, Throwable t) {
                    handleResponse(new NetworkResponseFailure(new Error<>(Error.NetworkError.NETWORK_ERROR)), myCallback);
                }
            });
        });
    }

    public void handleResponse(NetworkResponse networkResponse, MyCallback myCallback) {
        myHandler.post(() -> myCallback.onComplete(networkResponse));
    }

}
