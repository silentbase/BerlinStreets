package com.example.berlinstreets.berlinNetwork;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.berlinstreets.berlinModul.Marker;
import com.example.berlinstreets.berlinView.MapActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapRequest implements IMapRequest {

    private final String IP = "192.168.2.121";
    private final String PORT = "2000";

    /**
     * @param userID
     * @param title
     * @param street
     * @param houseNumber
     * @param postalcode
     * @param mapContext
     */
    public void addMarkerRequest(final String userID, final String title, final String street, final String houseNumber, final String postalcode, final String langt, final String longt, final Context mapContext) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://" + IP + ":" + PORT + "/map/addMarker", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Marker marker = gson.fromJson(response, Marker.class);

                if (marker.getUserID() == null) {
                    ((MapActivity) mapContext).markerFailed();
                    //TODO: ((MapActivity) mapContext).
                } else {
                    ((MapActivity) mapContext).markerSucceeded();
                    Log.d("bebe","!?!?!");
                    //saveMarkerData("markerData", mapContext, marker.getID());
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
                params.put("postalcode", postalcode);
                params.put("langt", langt);
                params.put("longt", longt);

                return params;
            }
        };
        RequestHandler.getInstance(mapContext).addToRequestQueue(postRequest);
    }

    /**
     * @param Id
     * @param mapContext
     */
    public void deleteMarkerRequest(String Id, final Context mapContext) {


        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, "http://" + IP + ":" + PORT + "/map/deleteMarker" + Id, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response == null) {
                    Toast.makeText(mapContext, response, Toast.LENGTH_SHORT).show();
                } else {
                    //TODO: delete from list
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestHandler.getInstance(mapContext).addToRequestQueue(deleteRequest);
    }

    /**
     * @param mapContext
     */
    public void getMarkerRequest(final Context mapContext) {

        StringRequest getRequest = new StringRequest(Request.Method.GET, "http://" + IP + ":" + PORT + "/map/getMarker", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Log.d("bebe", "h11212");
                if (response.length() != 0) {
                    Log.d("bebe", "!!!!");
                    Marker[] markers = gson.fromJson(response, Marker[].class);
                    ((MapActivity) mapContext).setMarkers(markers);
                    Log.d("bebe", response+"..null");
                } else {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestHandler.getInstance(mapContext).addToRequestQueue(getRequest);

    }

    /**
     * @param Id
     * @param mapContext
     */
    public void getMarkerOfCurrentUserRequest(String Id, final Context mapContext) {
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, "http://" + IP + ":" + PORT + "/map/getMarker" + Id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("");
                    JSONObject em = jsonArray.getJSONObject(2);
                    Gson g = new Gson();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestHandler.getInstance(mapContext).addToRequestQueue(getRequest);
    }

    private void saveMarkerData(String sharedPrefName, Context context, String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID", id);
    }
}
