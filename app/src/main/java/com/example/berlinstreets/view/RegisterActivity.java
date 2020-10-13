package com.example.berlinstreets.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.berlinstreets.R;
import com.example.berlinstreets.presenter.LoginPresenter;
import com.example.berlinstreets.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements ILoginRegisterView {

    private RegisterPresenter registerPresenter;

    private EditText firstnameEditText;
    private EditText surenameEditText;
    private EditText genderEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstnameEditText = findViewById(R.id.firstnameEditText);
        surenameEditText = findViewById(R.id.surenameEditText);
        genderEditText = findViewById(R.id.genderEditText);
        emailEditText = findViewById(R.id.emailEditTextRegister);
        passwordEditText = findViewById(R.id.passwordEditTextRegister);

        registerButton = findViewById(R.id.registerButton);

        registerPresenter= new RegisterPresenter(this, this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerPresenter.setData(firstnameEditText.getText().toString(), surenameEditText.getText().toString(), genderEditText.getText().toString(),
                        emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }

    @Override
    public void loginFailedAlert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}