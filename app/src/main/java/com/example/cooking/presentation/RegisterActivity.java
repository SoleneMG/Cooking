package com.example.cooking.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cooking.Inject;
import com.example.cooking.R;
import com.example.cooking.domain.RegisterActivityLogic;
import com.example.cooking.server.NetworkResponse;
import com.example.cooking.server.NetworkResponseFailure;
import com.example.cooking.server.NetworkResponseSuccess;
import com.example.cooking.server.ServerImpl;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final RegisterActivityLogic logic = Inject.registerActivityLogic();
    private EditText email, password;
    private Spinner spinner;
    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.language_label_spinner, R.layout.spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        if (item.equalsIgnoreCase("français")) {
            language = "fr";
        } else {
            language = "en";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Register(View view) {
        if (email != null && password != null && language != null) {
            String emailString = email.getText().toString();
            String passwordString = password.getText().toString();
            logic.register(emailString, passwordString, language, new ServerImpl.MyCallback() {
                @Override
                public void onComplete(NetworkResponse networkResponse) {
                    if(networkResponse instanceof NetworkResponseSuccess){
                        Intent intent = new Intent(view.getContext(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Snackbar.make(RegisterActivity.this, view, "It doesn't work. Error "+ ((NetworkResponseFailure) networkResponse).error.code, Snackbar.LENGTH_SHORT)
                                .setAction(R.string.refresh_button_snackbar, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                })
                        .show();
                    }
                }
            });
        }
    }



}