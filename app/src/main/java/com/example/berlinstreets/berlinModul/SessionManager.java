package com.example.berlinstreets.berlinModul;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.berlinstreets.berlinView.LoginActivity;
import com.example.berlinstreets.berlinView.MapActivity;

import java.util.HashMap;

public class SessionManager extends Application implements ISessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    private static final String PREF_NAME = "Login";
    private static final String LOGIN = "is_Login";
    private static final String ID = "Id";
    private static final String EMAIL = "email";
    private static final String FIRSTNAME = "firstname";


    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String Id, String email, String firstname) {
        editor.putBoolean(LOGIN, true);
        editor.putString(ID, Id);
        editor.putString(EMAIL, email);
        editor.putString(FIRSTNAME, firstname);

        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLoginAppStart() {
        if (isLoggedIn()) {
            context.startActivity(new Intent(context, MapActivity.class));
            ((Activity) context).finish();
        }
    }

    public void checkLoginOnMap() {
        if (!isLoggedIn()) {
            context.startActivity(new Intent(context, LoginActivity.class));
            ((Activity) context).finish();
        }
    }

    public void logout() {
        editor.clear();
        editor.commit();
        //context.startActivity(new Intent(context, LoginActivity.class));
        ((Activity) context).recreate();
    }

    public HashMap<String, String> getUserData() {
        HashMap<String, String> user = new HashMap<>();
        user.put(ID, sharedPreferences.getString(ID, ""));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, ""));
        user.put(FIRSTNAME, sharedPreferences.getString(FIRSTNAME, ""));

        return user;
    }
}
