package com.example.berlinstreets.network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.berlinstreets.modul.Marker;
import com.example.berlinstreets.view.MapActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MapRequest {

    private final String IP = "192.168.2.121";
    private final String PORT = "2000";

    public void addMarkerRequest(final String userID, final String title, final String street, final String houseNumber, final String postcode, final Context mapContext) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://" + IP + ":" + PORT + "/user/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Marker marker = gson.fromJson(response, Marker.class);

                if (marker.getUserID() == null) {
                    Toast.makeText(mapContext, "", Toast.LENGTH_SHORT).show();
                    //TODO: ((MapActivity) mapContext).
                } else {
                    saveMarkerData("markerData", mapContext, marker.getID());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userID", userID);
                params.put("title", title);
                params.put("street", street);
                params.put("houseNumber", houseNumber);
                params.put("postcode", postcode);

                return params;
            }
        };
        RequestHandler.getInstance(mapContext).addToRequestQueue(postRequest);
    }

    private void saveMarkerData(String sharedPrefName, Context context, String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID", id);
    }
}
