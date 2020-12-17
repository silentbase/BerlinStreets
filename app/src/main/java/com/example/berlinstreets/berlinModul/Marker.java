package com.example.berlinstreets.berlinModul;

import android.content.Context;

import com.example.berlinstreets.berlinNetwork.MapRequest;

public class Marker implements IMarker {
    private String ID;
    private String userID;
    private String title;
    private String street;
    private String houseNumber;
    private String postalcode;

    private String longt;
    private String langt;

    private Context mapContext;

    public Marker() {

    }

    public Marker(String userID, String title, String street, String houseNumber, String postalcode, String langt, String longt, Context mapContext) {
        this.userID = userID;
        this.title = title;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalcode = postalcode;
        this.langt = langt;
        this.longt = longt;
        this.mapContext = mapContext;
    }

    public Marker(String userId, Context mapContext) {
        this.userID = userID;
        this.mapContext = mapContext;
    }

    public boolean isMarkerDataValid() {
        if (userID == null || title == null || street == null || houseNumber == null || postalcode == null) {
            return false;
        }
        if (userID.length() < 1 || title.length() < 1 || street.length() < 1 || houseNumber.length() < 1 || postalcode.length() < 1) {
            return false;
        }

        int userIDSpace = userID.trim().indexOf(" ");
        int titleSpace = title.trim().indexOf(" ");
        int streetSpace = street.trim().indexOf(" ");
        int houseNumberSpace = houseNumber.trim().indexOf(" ");
        int postcodeSpace = postalcode.trim().indexOf(" ");

        return true;
    }

    public void sendAddMarkerRequest() {
        MapRequest mapRequest = new MapRequest();
        mapRequest.addMarkerRequest(userID, title, street, houseNumber, postalcode, langt, longt, mapContext);
    }

    public void sendGetMarkers() {
        MapRequest mapRequest = new MapRequest();
        mapRequest.getMarkerRequest(mapContext);
    }

    public void sendGetMarkersOfUser() {
        MapRequest mapRequest = new MapRequest();
        mapRequest.getMarkerOfCurrentUserRequest(userID, mapContext);
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

    public String getPostalcode() {
        return postalcode;
    }

    public float getLongt() {
        return Float.parseFloat(longt);
    }

    public float getLangt() {
        return Float.parseFloat(langt);
    }
}
