package com.example.berlinstreets.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import com.example.berlinstreets.R;
import com.example.berlinstreets.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginRegisterView {

    private LoginPresenter loginPresenter;

    private EditText email;
    private EditText password;
    private Button signInButton;
    private TextView notRegistered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.emailEdittext);
        password = findViewById(R.id.passwordEdittext);

        signInButton = findViewById(R.id.signInButton);
        notRegistered = findViewById(R.id.notRegisteredTextView);


        loginPresenter = new LoginPresenter(this, this);

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
}