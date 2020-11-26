package com.example.berlinstreets.network;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.berlinstreets.modul.User;
import com.example.berlinstreets.view.LoginActivity;
import com.example.berlinstreets.view.MapsActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class UserRequest implements UserRequestInterface {

    private LoginActivity loginActivity;
    private final String IP = "192.168.2.121";
    private final String PORT = "2000";

    public UserRequest() {

    }

    /**
     * POST-REQUEST
     * parameters are final, cause they're accessed from within inner class
     */
    public void loginRequest(final String email, final String password, final Context loginContext) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://"+IP+":2000/user/login", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("O", "onResponse!");
                Gson gson = new Gson();
                User user = gson.fromJson(response, User.class);

                if (user.getID() == null) {
                    Toast.makeText(loginContext, "Email-Passoword Kombination ist nicht korrekt", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    saveData("userData", loginContext, user.getID(),user.getEmail(),user.getFirstname());
                    loginContext.startActivity(new Intent(loginContext, MapsActivity.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(loginContext, error.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void registerRequest(final String firstname, final String surename, final String gender, final String email, final String password, final Context loginContext) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://"+IP+":2000/user/register", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(loginContext, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        RequestHandler.getInstance(loginContext).addToRequestQueue(postRequest);
    }

    private void saveData(String sharedPrefName, Context context, String id, String email, String firstname) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID", id);
        editor.putString("EMAIL", email);
        editor.putString("FIRSTNAME", firstname);
    }
}
