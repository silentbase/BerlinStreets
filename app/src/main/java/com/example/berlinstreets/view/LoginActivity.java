package com.example.berlinstreets.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import com.example.berlinstreets.R;
import com.example.berlinstreets.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements IView {

    private EditText email;
    private EditText password;
    private Button signInButton;
    private TextView notRegistered;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ID = "ID";
    public static final String EMAIL = "email";
    public static final String FIRSTNAME = "first_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.emailEdittext);
        password = findViewById(R.id.passwordEdittext);

        signInButton = findViewById(R.id.signInButton);
        notRegistered = findViewById(R.id.notRegisteredTextView);


        final LoginPresenter loginPresenter = new LoginPresenter(this, this);

        /**
         * by clicking on the "not registered" textview the user switches to the RegisterActivity
         */
        notRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.setData(email.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void loginFailedAlert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void switchActivity() {
        startActivity(new Intent(LoginActivity.this, MapActivity.class));
    }

    @Override
    public void saveData(String id, String email, String firstname) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(ID, id);
        editor.putString(EMAIL, email);
        editor.putString(FIRSTNAME, firstname);
    }
}