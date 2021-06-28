package com.example.cooking.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.cooking.Inject;
import com.example.cooking.R;
import com.example.cooking.domain.LoginActivityLogic;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private LoginActivityLogic logic = Inject.loginActivityLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void Login(View view) {
        if (email != null && password != null) {
            String emailString = email.getText().toString();
            String passwordString = password.getText().toString();
            logic.login(emailString, passwordString);
        }
    }

}