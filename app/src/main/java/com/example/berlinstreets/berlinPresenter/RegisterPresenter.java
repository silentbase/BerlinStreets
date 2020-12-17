package com.example.berlinstreets.berlinPresenter;

import android.content.Context;
import android.widget.Toast;

import com.example.berlinstreets.berlinModul.SessionManager;
import com.example.berlinstreets.berlinModul.User;

public class RegisterPresenter implements IPresenter {

    private User user;
    private SessionManager sessionManager;
    private Context registerContext;

    /**
     * constructer, that takes in a context
     */
    public RegisterPresenter(Context registerContext) {
        this.registerContext = registerContext;
    }

    public SessionManager getSession(Context context) {
        sessionManager = new SessionManager(context);
        return sessionManager;
    }

    @Override
    public void setData(String... data) {
        user = new User(data[0], data[1], data[2], data[3], data[4], registerContext);

        if (!user.isRegisterDataValid()) {
            Toast.makeText(registerContext, "Registrierung fehlgeschlagen", Toast.LENGTH_SHORT).show();

        } else {
            user.sendRegisterRequest();
        }
    }
}
