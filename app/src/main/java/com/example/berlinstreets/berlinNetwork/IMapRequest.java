package com.example.berlinstreets.berlinNetwork;

import android.content.Context;

public interface IMapRequest {

    /**
     *
     * @param userID
     * @param title
     * @param street
     * @param houseNumber
     * @param postcode
     * @param mapContext
     */
    void addMarkerRequest(String userID, String title, String street, String houseNumber, String postcode, String langt, String longt, Context mapContext);
}
