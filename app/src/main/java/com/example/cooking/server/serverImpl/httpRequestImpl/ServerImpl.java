package com.example.cooking.server.serverImpl.httpRequestImpl;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.core.os.HandlerCompat;

import com.example.cooking.server.MyCallback;
import com.example.cooking.server.model.NetworkResponse;
import com.example.cooking.server.model.NetworkResponseSuccess;
import com.example.cooking.server.Server;
import com.example.cooking.server.model.json.ErrorJson;
import com.example.cooking.server.model.register.RegisterJson;
import com.example.cooking.model.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.cooking.server.serverImpl.retrofitImpl.RetrofitImpl.EXECUTOR;

public class ServerImpl implements Server {

    private final Handler myHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    private final String urlRegister = "http://192.168.1.17:8080/api/1/register";
    HttpURLConnection httpURLClient;
    public final Gson gson = new Gson();

    @Override
    public void sendPostRegister(String email, String password, String language, MyCallback myCallback) {
        RegisterJson registerJson = new RegisterJson(email, password, language);
        String jsonBody = gson.toJson(registerJson);
        httpRequest(jsonBody, myCallback);
    }

    @Override
    public void sendPostLogin(String email, String password, MyCallback myCallback) {

    }


    private String readStream(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        return sb.toString();
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.d("DEBUG", "Closeable not closed" + closeable);
            }
        }
    }

    public void httpRequest(final String jsonBody, final MyCallback myCallback) {
        EXECUTOR.submit(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = null;
                OutputStream outputStream = null;
                String response;
                try {
                    httpURLClient = (HttpURLConnection) new URL(urlRegister).openConnection();
                    httpURLClient.setDoOutput(true);
                    httpURLClient.setRequestProperty("Content-Type", "application/json");
                    httpURLClient.setRequestProperty("Accept", "application/json");
                    httpURLClient.setRequestMethod("POST");

                    outputStream = httpURLClient.getOutputStream();
                    outputStream.write(jsonBody.getBytes());
                    outputStream.flush();

                    int status = httpURLClient.getResponseCode();

                    if (status >= 200 && status < 300) {
                        inputStream = httpURLClient.getInputStream();
                        response = readStream(inputStream);
                        User user = gson.fromJson(response, User.class);
                        handleResponse(new NetworkResponseSuccess(new User(user.publicId, user.id, user.email)), myCallback);
                    } else {
                        inputStream = httpURLClient.getErrorStream();
                        response = readStream(inputStream);
                        ErrorJson errorJson = gson.fromJson(response, ErrorJson.class);
                       // handleResponse(new NetworkResponseFailure(new Error(errorJson.error.code, errorJson.error.status, errorJson.error.reasonCode, errorJson.error.reasonStatus)), myCallback);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close(outputStream);
                    close(inputStream);
                    httpURLClient.disconnect();
                }
            }
        });
    }

    public void handleResponse(NetworkResponse networkResponse, MyCallback myCallback){
        myHandler.post(new Runnable() {
            @Override
            public void run() {
                myCallback.onCompleteLoginCall(networkResponse);
            }
        });
    }
}
