package com.example.berlinstreets.berlinPresenter;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.berlinstreets.berlinModul.Marker;
import com.example.berlinstreets.berlinModul.SessionManager;
import com.example.berlinstreets.berlinView.MapActivity;

public class MapPresenter implements IPresenter {

    private Marker marker;
    private SessionManager sessionManager;
    private Context mapContext;

    public MapPresenter(Context mapContext) {
        this.mapContext = mapContext;
    }

    public SessionManager getSession() {
        sessionManager = new SessionManager(mapContext);
        return sessionManager;
    }

    @Override
    public void setData(String... strings) {
        if (strings.length == 7) {
            marker = new Marker(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5], strings[6], mapContext);
        } else {
            marker = new Marker(strings[0], mapContext);

        }
    }

    public void addMarker() {
        if (marker.isMarkerDataValid()) {
            marker.sendAddMarkerRequest();

        } else {

            ((MapActivity) mapContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((MapActivity) mapContext).onBackPressed();
                    Toast.makeText(mapContext, "Keine Adresse für diesen Marker gefunden", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void getAllMarkers() {
            marker.sendGetMarkers();
    }

    public void getUserMarkers() {
        if (marker.isMarkerDataValid())
            marker.sendGetMarkersOfUser();
    }
}


