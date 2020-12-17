package com.example.berlinstreets.berlinPresenter;

import android.content.Context;
import android.widget.Toast;

import com.example.berlinstreets.berlinModul.SessionManager;
import com.example.berlinstreets.berlinModul.User;

public class LoginPresenter implements IPresenter {

    private User user;
    private SessionManager sessionManager;
    private Context loginContext;

    public LoginPresenter(Context loginContext) {
        this.loginContext = loginContext;
    }

    public SessionManager getSession(Context context) {
        sessionManager = new SessionManager(context);
        return sessionManager;
    }

    @Override
    public void setData(String... data) {
        user = new User(data[0], data[1], loginContext);

        if (!user.isLoginDataValid()) {

            Toast.makeText(loginContext, "Daten nicht korrekt\nBitte versuche es nochmal", Toast.LENGTH_SHORT).show();
        } else {
            user.sendLoginRequest();
        }
    }
}
