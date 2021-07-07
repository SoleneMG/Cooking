package com.example.cooking.data.server.model.json;

public class TokenJson {
    public final String refreshToken;
    public final String accessToken;

    public TokenJson(String refreshToken, String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
