package com.example.berlinstreets.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.berlinstreets.R;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.usernameEdittext);
        password = findViewById(R.id.passwordEdittext);

    }
    public void signIn(View view){
        getLoginData();
    }
    public String[] getLoginData(){
        String[] s = new String[]{username.getText().toString(), password.getText().toString()};
        return s;
    }
}