package com.example.berlinstreets.presenter;

import android.content.Context;

import com.example.berlinstreets.modul.Marker;
import com.example.berlinstreets.view.IView;

public class MapPresenter implements IPresenter {

    private Marker marker;

    private IView mapView;
    private Context mapContext;

    public MapPresenter(IView mapView, Context mapContext) {
        this.mapView = mapView;
        this.mapContext = mapContext;
    }

    @Override
    public void setData(String... data) {
        marker = new Marker(data[0],data[1],data[2],data[3],data[4], mapContext);

        if(marker.isMarkerDataValid()){
            marker.addMarkerRequest();
        }
    }
}
