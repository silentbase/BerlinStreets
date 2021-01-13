package com.example.berlinstreets.berlinNetwork;

import android.content.Context;

public interface IMapRequest {

    /**
     * sends a post-request to add a marker
     * @param userID
     * @param title
     * @param street
     * @param houseNumber
     * @param postcode
     * @param mapContext
     */
    void addMarkerRequest(String userID, String title, String street, String houseNumber, String postcode, String langt, String longt, Context mapContext);

    /**
     * sends a delete-request to delete a marker
     * @param markerId
     * @param mapContext
     */
    void deleteMarkerRequest(String markerId, final Context mapContext);

    /**
     * sends a get-request to get all markers
     * @param mapContext
     */
    void getMarkerRequest(final Context mapContext);

    /**
     * sends a get-request to get the markers of the current user
     * @param userId
     * @param mapContext
     */
    void getMarkerOfCurrentUserRequest(String userId, final Context mapContext);
}
