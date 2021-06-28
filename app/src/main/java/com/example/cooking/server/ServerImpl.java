package com.example.cooking.server;


import com.example.cooking.server.model.RegisterJson;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerImpl implements Server {
    public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(1);
    private String urlRegister = "http://192.168.1.17:8080/api/1/register";
    private String urlConnexion = "http://192.168.1.17:8080/api/1/connexion";
    HttpURLConnection httpURLClient;
    public final Gson gson = new Gson();


    private void sendGet(String url) throws Exception {
        EXECUTOR.submit(() -> {
            try {
                HttpURLConnection httpURLClient = (HttpURLConnection) new URL(url).openConnection();
                readStream();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    @Override
    public void sendPost(String email, String password, String language) {
        RegisterJson registerJson = new RegisterJson(email, password, language);
        String jsonBody = gson.toJson(registerJson);
        EXECUTOR.submit(() -> {
            try {
                httpURLClient = (HttpURLConnection) new URL(urlRegister).openConnection();
                httpURLClient.setDoOutput(true);
                httpURLClient.setRequestProperty("Content-Type", "application/json");
                httpURLClient.setRequestProperty("Accept", "application/json");
                httpURLClient.setRequestMethod("POST");

                OutputStream outputStream = httpURLClient.getOutputStream();
                outputStream.write(jsonBody.getBytes());
                outputStream.flush();
                outputStream.close();


                String response = readStream();
                System.out.println(response);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLClient.disconnect();
            }

        });
    }


    private String readStream() throws IOException {
        int status = httpURLClient.getResponseCode();
        InputStream inputStream;

        if (status >= 200 && status < 300) {
            inputStream = httpURLClient.getInputStream();
        } else {
            inputStream = httpURLClient.getErrorStream();
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        inputStream.close();
        return sb.toString();
    }
}