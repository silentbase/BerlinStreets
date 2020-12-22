package com.example.berlinstreets.berlinView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.berlinstreets.R;
import com.example.berlinstreets.berlinModul.SessionManager;
import com.example.berlinstreets.berlinPresenter.MapPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Buttons in der main_activity
    Button login;
    Button register;
    Button map;
    Button logout;
    TextView begruessung;

    private MapPresenter mapPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mapPresenter = new MapPresenter(this);

        if (mapPresenter.getSession().isLoggedIn()) {
            setContentView(R.layout.activity_main_logged_in);
            logout = findViewById(R.id.logoutButton);
            begruessung = findViewById(R.id.berlinStreetTextView);
            logout.setOnClickListener(this);
            begruessung.setText("Hallo, " + mapPresenter.getSession().getUserData().get("firstname"));
            ;
        } else {
            setContentView(R.layout.activity_main);
            login = findViewById(R.id.loginButton);
            register = findViewById(R.id.registerButtonMain);

            login.setOnClickListener(this);
            register.setOnClickListener(this);
        }

        map = findViewById(R.id.mapButton);

        map.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mapButton:
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
                break;
            case R.id.loginButton:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.registerButtonMain:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            case R.id.logoutButton:
                mapPresenter.getSession().logout();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}