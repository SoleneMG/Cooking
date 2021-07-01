package com.example.cooking.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.cooking.Inject;
import com.example.cooking.R;
import com.example.cooking.domain.LoginActivityLogic;
import com.example.cooking.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private LoginActivityLogic logic = Inject.loginActivityLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Intent intent = getIntent();
        User user = intent.getParcelableExtra(RegisterActivity.EXTRA_MESSAGE);
        email.setText(user.email);
    }

    public void Login(View view) {
        if (email != null && password != null) {
            String emailString = email.getText().toString();
            String passwordString = password.getText().toString();
            logic.login(emailString, passwordString);
        }
    }

}