package com.example.berlinstreets.modul;

import android.content.Context;

import com.example.berlinstreets.network.MapRequest;

public class Marker {
    private String ID;
    private String userID;
    private String title;
    private String street;
    private String houseNumber;
    private String postcode;

    private Context mapContext;

    public Marker(String userID, String title, String street, String houseNumber, String postcode, Context mapContext) {
        this.userID = userID;
        this.title = title;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.mapContext = mapContext;
    }

    public boolean isMarkerDataValid(){
        if (userID.length() < 1 || title.length() < 1 || street.length() < 1 || houseNumber.length() < 1 || postcode.length() < 1) {
            return false;
        }

        int userIDSpace = userID.trim().indexOf(" ");
        int titleSpace = title.trim().indexOf(" ");
        int streetSpace = street.trim().indexOf(" ");
        int houseNumberSpace = houseNumber.trim().indexOf(" ");
        int postcodeSpace = postcode.trim().indexOf(" ");

        if (userIDSpace != -1 || titleSpace != -1 || streetSpace != -1 || houseNumberSpace != -1 || postcodeSpace != -1) {
            return false;
        }
        return true;
    }

    public void addMarkerRequest() {
        MapRequest mapRequest = new MapRequest();
        mapRequest.addMarkerRequest(userID,title, street,houseNumber,postcode, mapContext);
    }

    /*************************
     GETTERS
     *************************/

    public String getID() {
        return ID;
    }

    public String getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostcode() {
        return postcode;
    }
}
