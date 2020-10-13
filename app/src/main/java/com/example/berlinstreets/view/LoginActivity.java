package com.example.berlinstreets.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
<<<<<<< HEAD
=======
import android.widget.TextView;
import android.widget.Toast;
>>>>>>> 53523d5... first commit | login -, registerActivities after the MVP-Pattern | http requests for login and registration

import com.example.berlinstreets.R;
import com.example.berlinstreets.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginRegisterView {

<<<<<<< HEAD
    EditText username;
    EditText password;
=======
    private LoginPresenter loginPresenter;

    private EditText email;
    private EditText password;
    private Button signInButton;
    private TextView notRegistered;
>>>>>>> 53523d5... first commit | login -, registerActivities after the MVP-Pattern | http requests for login and registration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.emailEdittext);
        password = findViewById(R.id.passwordEdittext);
<<<<<<< HEAD

    }
    public void signIn(View view){
        getLoginData();
    }
    public String[] getLoginData(){
        String[] s = new String[]{username.getText().toString(), password.getText().toString()};
        return s;
=======
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
>>>>>>> 53523d5... first commit | login -, registerActivities after the MVP-Pattern | http requests for login and registration
    }
}