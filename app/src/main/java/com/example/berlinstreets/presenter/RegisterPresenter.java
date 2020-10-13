package com.example.berlinstreets.presenter;

import android.content.Context;

import com.example.berlinstreets.modul.User;
import com.example.berlinstreets.view.ILoginRegisterView;

public class RegisterPresenter implements IPresenter {

    private User user;

    private ILoginRegisterView registerView;
    private Context registerContext;

    /**
     * constructer, that takes in a context
     */
    public RegisterPresenter(ILoginRegisterView registerView, Context registerContext) {
        this.registerView = registerView;
        this.registerContext = registerContext;
    }


    @Override
    public void setData(String... data) {
        user = new User(data[0], data[1], data[2], data[3], data[4], registerContext);

        if (!user.isRegisterDataValid()) {
            registerView.loginFailedAlert("Registrierung fehlgeschlagen");

        } else {
            user.sendRegisterRequest();
        }
    }
}
