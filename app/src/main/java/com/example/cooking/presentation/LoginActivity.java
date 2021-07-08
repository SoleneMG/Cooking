package com.example.cooking.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.cooking.Inject;
import com.example.cooking.MyApplication;
import com.example.cooking.R;
import com.example.cooking.data.database.CookingDatabase;
import com.example.cooking.data.database.databaseImpl.RoomDatabaseImpl;
import com.example.cooking.data.database.databaseImpl.UserDao;
import com.example.cooking.domain.LoginLogic;
import com.example.cooking.model.Error;
import com.example.cooking.model.Token;
import com.example.cooking.model.User;
import com.example.cooking.data.server.MyCallback;
import com.example.cooking.data.server.model.NetworkResponse;
import com.example.cooking.data.server.model.NetworkResponseFailure;
import com.example.cooking.data.server.model.NetworkResponseSuccess;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private LoginLogic logic = Inject.loginActivityLogic();
    private User user;
    private CookingDatabase database = Inject.database();
    private UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Intent intent = getIntent();
        user = intent.getParcelableExtra(RegisterActivity.EXTRA_MESSAGE);
        email.setText(user.email);
        // TESTING
        password.setText(R.string.password_testing);
    }

    public void Login(View view) {
        if (email != null && password != null) {
            String emailString = email.getText().toString();
            String passwordString = password.getText().toString();
            logic.login(emailString, passwordString, new MyCallback() {
                @Override
                public void onCompleteRegisterCall(NetworkResponse<Error.RegisterError, User> networkResponse) {
                    // do nothing
                }

                @Override
                public void onCompleteLoginCall(NetworkResponse<Error.LoginError, Token> networkResponse) {
                    if (networkResponse instanceof NetworkResponseSuccess) {
                        userDao = ((RoomDatabaseImpl) database).userDao();
                        MyApplication.EXECUTOR.execute(new Runnable() {
                            @Override
                            public void run() {
                                userDao.insert(user);
                            }
                        });
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);

                    } else {
                        Error.LoginError loginError = ((NetworkResponseFailure<Error.LoginError, Token>) networkResponse).eError.error;
                        Snackbar.make(LoginActivity.this, view, getString(R.string.error_message)+ " "+loginError.name(), Snackbar.LENGTH_LONG)
                                .setBackgroundTint(getColor(R.color.primary))
                                .setActionTextColor(getColor(R.color.white))
                                .setTextColor(getColor(R.color.white))
                                .setDuration(2000)
                                .show();
                    }
                }
            });
        }
    }

}