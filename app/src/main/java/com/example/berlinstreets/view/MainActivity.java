package com.example.berlinstreets.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.berlinstreets.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Buttons in der main_activity
    Button login;
    Button register;
    Button map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = findViewById(R.id.mapButton);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButtonMain);

        map.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.mapButton:
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
                break;
            case R.id.loginButton:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.registerButtonMain:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
        }
    }
}