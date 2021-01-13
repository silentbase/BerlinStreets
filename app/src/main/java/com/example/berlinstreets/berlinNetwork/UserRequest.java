package com.example.berlinstreets.berlinNetwork;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.berlinstreets.berlinModul.SessionManager;
import com.example.berlinstreets.berlinModul.User;
import com.example.berlinstreets.berlinPresenter.LoginPresenter;
import com.example.berlinstreets.berlinView.LoginActivity;
import com.example.berlinstreets.berlinView.MainActivity;
import com.example.berlinstreets.berlinView.MapsActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class UserRequest implements IUserRequest {

    private LoginPresenter loginPresenter;

    private final String URL = "https://berlinstreets.herokuapp.com";

    public UserRequest() {

    }

    /**
     * POST-REQUEST
     * parameters are final, cause they're accessed from within inner class
     */
    public void loginRequest(final String email, final String password, final Context loginContext) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL+"/user/login", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                User user = gson.fromJson(response, User.class);

                if (user.getID() == null) {
                    Toast.makeText(loginContext, "Email-Passoword Kombination ist nicht korrekt", Toast.LENGTH_SHORT).show();
                } else {
                    loginPresenter = new LoginPresenter(loginContext);
                    loginPresenter.createSession(user.getID(), user.getEmail(), user.getFirstname());
                    loginContext.startActivity(new Intent(loginContext, MapsActivity.class));
                    ((Activity) loginContext).finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(loginContext, "error.getMessage()", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Log.i("I", "haaahaaaa");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        RequestHandler.getInstance(loginContext).addToRequestQueue(postRequest);
    }

    public void registerRequest(final String firstname, final String surename, final String gender, final String email, final String password, final Context registerContext) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL+"/user/register", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                User user = gson.fromJson(response, User.class);

                if (user.getID() == null) {
                    Toast.makeText(registerContext, "Registrierung fehlgeschlagen", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(registerContext, LoginActivity.class);
                    intent.putExtra("EMAIL", email);

                    registerContext.startActivity(intent);
                    ((Activity) registerContext).finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(registerContext, "Diese E-Mail existiert bereits", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("firstname", firstname);
                params.put("surename", surename);
                params.put("gender", gender);
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        RequestHandler.getInstance(registerContext).addToRequestQueue(postRequest);
    }
}
