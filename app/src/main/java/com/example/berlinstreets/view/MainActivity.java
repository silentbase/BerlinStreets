package com.example.berlinstreets.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.berlinstreets.R;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
=======
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
>>>>>>> 53523d5... first commit | login -, registerActivities after the MVP-Pattern | http requests for login and registration

    //Database

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

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    public class dB extends AsyncTask<String, Void, Void> {

<<<<<<< HEAD
        @Override
        protected Void doInBackground(String... strings) {

            String url = "jdbc:postgresql://localhost/postgres";
            String user = "postgres";
            String password = "onepiece123data";

            Connection connection = null;
            Statement statement = null;

            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                if (connection != null) {
                    Log.e("DB", "--------------------------------------------YES------------------------------------");
                } else {
                    Log.e("DB", "--------------------------------------------NO------------------------------------");
                }
                String query = "create table Userasfa(id int,name varchar (20),vorname varchar (20))";
                //"create table hahahaha(PersNr integer not null,Name varchar (30) not null,Rang character (2),PRIMARY KEY (PersNr))";

                statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
=======
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
>>>>>>> 53523d5... first commit | login -, registerActivities after the MVP-Pattern | http requests for login and registration
        }
    }
}