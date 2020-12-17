package com.example.berlinstreets.berlinView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;


import com.example.berlinstreets.R;
import com.example.berlinstreets.berlinModul.SessionManager;
import com.example.berlinstreets.berlinPresenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signInButton;
    private TextView notRegistered;

    private SessionManager sessionManager;

   /* @Override
    protected void onStart() {
        sessionManager = new SessionManager(this);
        sessionManager.checkLoginAppStart();
        super.onStart();
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //sessionManager = new SessionManager(this);
        //sessionManager.checkLoginAppStart();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailEdittext);
        password = findViewById(R.id.passwordEdittext);

        signInButton = findViewById(R.id.signInButton);
        notRegistered = findViewById(R.id.notRegisteredTextView);

        sessionManager = new SessionManager(this);
        if (sessionManager.isLoggedIn()) {
            email.setText(sessionManager.getUserData().get("email"));
        }

        final LoginPresenter loginPresenter = new LoginPresenter(this);

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
}