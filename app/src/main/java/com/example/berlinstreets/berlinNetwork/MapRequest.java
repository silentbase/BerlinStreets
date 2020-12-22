package com.example.berlinstreets.berlinNetwork;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.berlinstreets.berlinModul.Marker;
import com.example.berlinstreets.berlinModul.SessionManager;
import com.example.berlinstreets.berlinView.MapsActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MapRequest implements IMapRequest {

    private final String IP = "192.168.2.121";
    private final String PORT = "2000";

    private SessionManager sessionManager;

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
                    ((MapsActivity) mapContext).markerFailed();
                    //TODO: ((MapActivity) mapContext).
                } else {
                    ((MapsActivity) mapContext).markerSucceeded();
                    Log.d("bebe", "!?!?!");
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
     * @param markerId
     * @param mapContext
     */
    public void deleteMarkerRequest(String markerId, final Context mapContext) {


        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, "http://" + IP + ":" + PORT + "/map/deleteMarker/" + markerId, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Toast.makeText(mapContext, "Marker wurde gelöscht!", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO: delete from list
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mapContext, "Fehler", Toast.LENGTH_SHORT).show();
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
                Marker[] markers;

                if (response.length() != 0) {
                    markers = gson.fromJson(response, Marker[].class);
                    ((MapsActivity) mapContext).setMarkers(markers);
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
     * @param userId
     * @param mapContext
     */
    public void getMarkerOfCurrentUserRequest(String userId, final Context mapContext) {
        StringRequest getRequest = new StringRequest(Request.Method.GET, "http://" + IP + ":" + PORT + "/map/getMarker/" + userId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                Marker[] markers;

                if (response.length() != 0) {
                    Log.d("bebe", "setMarkers!!");
                    markers = gson.fromJson(response, Marker[].class);
                    ((MapsActivity) mapContext).setListData(markers);
                    Log.d("sese", markers[0].get_Id());

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
}
