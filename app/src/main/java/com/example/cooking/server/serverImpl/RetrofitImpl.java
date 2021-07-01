package com.example.cooking.server.serverImpl;

import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import com.example.cooking.model.Error;
import com.example.cooking.model.User;
import com.example.cooking.server.MyCallback;
import com.example.cooking.server.Server;
import com.example.cooking.server.model.ErrorRetrofitResponse;
import com.example.cooking.server.model.NetworkResponse;
import com.example.cooking.server.model.NetworkResponseFailure;
import com.example.cooking.server.model.NetworkResponseSuccess;
import com.example.cooking.server.model.RegisterJson;
import com.example.cooking.server.model.UserJson;
import com.example.cooking.server.model.UserNetworkResponseJson;
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
            Call<UserNetworkResponseJson> call = cookServer.postRegister(new RegisterJson(email, password, language));
            call.enqueue(new Callback<UserNetworkResponseJson>() {
                @Override
                public void onResponse(Call<UserNetworkResponseJson> call, Response<UserNetworkResponseJson> response) {
                    if (response.body() != null && response.isSuccessful() && response.errorBody() == null) {
                        handleResponse(new NetworkResponseSuccess<>(new User(response.body().data.publicId, response.body().data.id, response.body().data.email)), myCallback);
                    } else {
                          ErrorRetrofitResponse errorRetrofitResponse = null;
                        try {
                            errorRetrofitResponse = new Gson().fromJson(response.errorBody().string(), ErrorRetrofitResponse.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        /*
                             Converter<ResponseBody, UserNetworkResponseJson> converter = RetrofitImpl.this.retrofit.responseBodyConverter(UserNetworkResponseJson.class, new Annotation[0]);
                        UserNetworkResponseJson userNetworkResponseJson = null;
                        try{
                            userNetworkResponseJson = converter.convert(response.errorBody());
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                         */
                        switch (errorRetrofitResponse.errorJson.reasonCode) {
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
                                throw new IllegalArgumentException("Error not supported" + errorRetrofitResponse.errorJson.reasonCode);
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
