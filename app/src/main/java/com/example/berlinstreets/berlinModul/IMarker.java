package com.example.berlinstreets.berlinModul;

public interface IMarker {

    /**
     * checks whether marker data is valid
     * @return
     */

    boolean isMarkerDataValid();

    /**
     * sends Postrequest to save new Marker to db
     */
    void sendAddMarkerRequest();

    /**
     * sends Getrequest to get all markers
     */
    void sendGetMarkers();

    /**
     * sends Getrequest to get all markers of the current user
     */
    void sendGetMarkersOfUser();

    /**
     * sends Deleterequest to delete specific marker
     */
    void sendDeleteMarkerRequest();
}
