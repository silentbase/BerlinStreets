package com.example.berlinstreets.berlinModul;

public interface IMarker {

    /**
     * checks whether marker data is valid
     * @return
     */

    boolean isMarkerDataValid();

    /**
     * sends Postrequest to save new MArker to db
     */
    void sendAddMarkerRequest();
}
