package com.example.berlinstreets.network;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class UserRequest {

    public UserRequest(){

    }

    /**
     * POST-REQUEST
     * parameters are final, cause they're accessed from within inner class
     */
    public void loginRequest(final String email, final String password, final Context loginContext) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://192.168.2.121:2000/user/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(loginContext, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        RequestHandler.getInstance(loginContext).addToRequestQueue(postRequest);
    }


    public void registerRequest(final String firstname, final String surename, final String gender, final String email, final String password, final Context loginContext) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://192.168.2.121:2000/user/register", new Response.Listener<String>() {
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
}
