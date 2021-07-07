package com.example.cooking.data.server.serverImpl;

import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import com.example.cooking.data.server.CookServer;
import com.example.cooking.model.Error;
import com.example.cooking.model.User;
import com.example.cooking.data.server.MyCallback;
import com.example.cooking.data.server.Server;
import com.example.cooking.data.server.model.ErrorRetrofitResponse;
import com.example.cooking.data.server.model.NetworkResponse;
import com.example.cooking.data.server.model.NetworkResponseFailure;
import com.example.cooking.data.server.model.NetworkResponseSuccess;
import com.example.cooking.data.server.model.json.TokenJson;
import com.example.cooking.data.server.model.login.LoginJson;
import com.example.cooking.data.server.model.login.LoginNetworkResponseJson;
import com.example.cooking.data.server.model.register.RegisterJson;
import com.example.cooking.data.server.model.register.RegisterNetworkResponseJson;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImpl implements Server {
    public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(4);
    public final retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder().baseUrl("http://192.168.1.17:8080/").addConverterFactory(GsonConverterFactory.create()).build();
    public final CookServer cookServer = retrofit.create(CookServer.class);
    private final Handler myHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    @Override
    public void sendPostRegister(String email, String password, String language, MyCallback myCallback) {
        EXECUTOR.submit(() -> {
            Call<RegisterNetworkResponseJson> call = cookServer.postRegister(new RegisterJson(email, password, language));
            call.enqueue(new Callback<RegisterNetworkResponseJson>() {
                @Override
                public void onResponse(Call<RegisterNetworkResponseJson> call, Response<RegisterNetworkResponseJson> response) {
                    if (response.body() != null && response.isSuccessful() && response.errorBody() == null) {
                        handleResponse("register", new NetworkResponseSuccess<>(new User(response.body().data.publicId, response.body().data.id, response.body().data.email)), myCallback);
                    } else {
                        ErrorRetrofitResponse errorRetrofitResponse = null;
                        try {
                            errorRetrofitResponse = new Gson().fromJson(response.errorBody().string(), ErrorRetrofitResponse.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        switch (errorRetrofitResponse.errorJson.reasonCode) {
                            case 1:
                                handleResponse("register", new NetworkResponseFailure(new Error<>(Error.RegisterError.INVALID_EMAIL)), myCallback);
                                break;
                            case 2:
                                handleResponse("register", new NetworkResponseFailure(new Error<>(Error.RegisterError.INVALID_PASSWORD)), myCallback);
                                break;
                            case 3:
                                handleResponse("register", new NetworkResponseFailure(new Error<>(Error.RegisterError.INVALID_LANGUAGE)), myCallback);
                                break;
                            case 4:
                                handleResponse("register", new NetworkResponseFailure(new Error<>(Error.RegisterError.USER_ALREADY_EXIST)), myCallback);
                                break;
                            default:
                                throw new IllegalArgumentException("Error not supported" + errorRetrofitResponse.errorJson.reasonCode);
                        }
                    }
                }

                @Override
                public void onFailure(Call<RegisterNetworkResponseJson> call, Throwable t) {
                    handleResponse("register", new NetworkResponseFailure(new Error<>(Error.NetworkError.NETWORK_ERROR)), myCallback);
                }
            });
        });
    }

    @Override
    public void sendPostLogin(String email, String password, MyCallback myCallback) {
        EXECUTOR.submit(() -> {
            Call<LoginNetworkResponseJson> call = cookServer.postLogin(new LoginJson(email, password));
            call.enqueue(new Callback<LoginNetworkResponseJson>() {
                @Override
                public void onResponse(Call<LoginNetworkResponseJson> call, Response<LoginNetworkResponseJson> response) {
                    if (response.body() != null && response.errorBody() == null && response.isSuccessful()) {
                        handleResponse("login", new NetworkResponseSuccess<Error.LoginError, TokenJson>(new TokenJson(response.body().refreshToken, response.body().accessToken)), myCallback);
                    } else {
                        ErrorRetrofitResponse errorRetrofitResponse = null;
                        try {
                            errorRetrofitResponse = new Gson().fromJson(response.errorBody().string(), ErrorRetrofitResponse.class);
                            switch (errorRetrofitResponse.errorJson.reasonCode) {
                                case 0:
                                    handleResponse("login", new NetworkResponseFailure(new Error<>(Error.LoginError.USER_NOT_FOUND)), myCallback);
                                    break;
                                case 1:
                                    handleResponse("login", new NetworkResponseFailure(new Error<>(Error.LoginError.PASSWORD_NOT_MATCH)), myCallback);
                                    break;
                                case 2:
                                    handleResponse("login", new NetworkResponseFailure(new Error<>(Error.LoginError.INVALID_EMAIL)), myCallback);
                                    break;
                                case 3:
                                    handleResponse("login", new NetworkResponseFailure(new Error<>(Error.LoginError.INVALID_PASSWORD)), myCallback);
                                    break;
                                default:
                                    throw new IllegalArgumentException("Error not supported" + errorRetrofitResponse.errorJson.reasonCode);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginNetworkResponseJson> call, Throwable t) {
                    handleResponse("login", new NetworkResponseFailure(new Error<>(Error.NetworkError.NETWORK_ERROR)), myCallback);
                }
            });
        });
    }

    private void handleResponse(String method, NetworkResponse networkResponse, MyCallback myCallback) {
        if (method.equalsIgnoreCase("register")) {
            myHandler.post(() -> myCallback.onCompleteRegisterCall(networkResponse));
        } else if (method.equalsIgnoreCase("login")) {
            myHandler.post(() -> myCallback.onCompleteLoginCall(networkResponse));
        } else {
            throw new IllegalArgumentException("Method doesn't supported" + method);
        }

    }

}
