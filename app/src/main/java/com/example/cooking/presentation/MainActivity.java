package com.example.cooking.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cooking.Inject;
import com.example.cooking.R;
import com.example.cooking.domain.MainActivityLogic;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    MainActivityLogic logic = Inject.mainActivityLogic();
    private EditText email, password;
    private Spinner spinner;
    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            logic.connexion(emailString, passwordString, language);
        }
    }

    public void Connexion(View view) {
        if (email != null && password != null && language != null) {
            String emailString = email.getText().toString();
            String passwordString = password.getText().toString();
            logic.connexion(emailString, passwordString, language);
        }
    }
}