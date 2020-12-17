package com.example.berlinstreets.berlinView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.berlinstreets.R;
import com.example.berlinstreets.berlinModul.SessionManager;
import com.example.berlinstreets.berlinPresenter.MapPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, ConfirmMarkerFragment.OnFragmentInteractionListener {

    private MapPresenter mapPresenter;
    private GoogleMap mMap;
    private Marker marker;
    private MarkerOptions markerOptions;
    private Fragment fragment;

    private LatLng latLng;
    private List<Address> addressList;
    //MarkerINFO
    private String title;

    /**
     * stores all markers that were received from the server
     */
    private com.example.berlinstreets.berlinModul.Marker[] markers;
    private Thread geoCoderThread;
    private Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fragment = ConfirmMarkerFragment.newInstance();
        mapPresenter = new MapPresenter(MapActivity.this);
        mapPresenter.setData(mapPresenter.getSession().getUserData().get("Id"));

        mapPresenter.getAllMarkers();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(52.5198378,13.4045256)));
        mMap.setMinZoomPreference(10.5f);

        // Add a marker in Sydney and move the camera
        markerOptions = new MarkerOptions();

        /*com.example.berlinstreets.berlinModul.Marker[] markers = new com.example.berlinstreets.berlinModul.Marker[1];
        markers[0] = new com.example.berlinstreets.berlinModul.Marker("","","","","","21","76",this); */

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    byte t = 0;
                    if (getMarkers() != null) {
                        for (int i = 0; i < getMarkers().length; i++) {
                            markerOptions = new MarkerOptions();
                            final LatLng pos = new LatLng(getMarkers()[i].getLangt(), getMarkers()[i].getLongt());
                            final int finalI = i;
                            final MarkerOptions finalMarkerOptions = markerOptions;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    mMap.addMarker(finalMarkerOptions.position(pos).title(getMarkers()[finalI].getTitle()));
                                }
                            });
                        }
                        break;
                    }
                    try {
                        Thread.sleep(400);
                        t++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (t == 5) {
                        break;
                    }
                }
            }
        });
        t.start();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {
                if (!mapPresenter.getSession().isLoggedIn()) {
                    startActivity(new Intent(MapActivity.this, LoginActivity.class));
                } else {
                    //geoCoder is in a separate thread, due to its long execution time
                    geoCoderThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            markerOptions = new MarkerOptions();
                            markerOptions.position(latLng);

                            setLatLng(latLng);

                            addressList = new ArrayList<>();
                            Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());

                            try {
                                addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 2);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    geoCoderThread.start();
                    confirmMarker();
                }

            }
        });
    }

    public com.example.berlinstreets.berlinModul.Marker[] getMarkers() {
        return markers;
    }

    public void setMarkers(com.example.berlinstreets.berlinModul.Marker... markers) {
        this.markers = markers;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void confirmMarker() {
        getSupportFragmentManager().beginTransaction().replace(R.id.map, fragment).commit();

    }

    public void markerSucceeded() {
        marker = mMap.addMarker(markerOptions);
        this.onBackPressed();
    }

    public void markerFailed() {

    }

    @Override
    public void onFragmentInteraction(final String title) {
        this.title = title;
        if (addressList.size() != 0) {
            Log.d("bebe", addressList.get(0).toString());
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                byte t = 0;
                while (true) {

                    if (addressList.size() > 0) { //TODO: || addressList.get(0).getStuff != null ||..

                        mapPresenter.setData(mapPresenter.getSession().getUserData().get("Id"), title, addressList.get(0).getThoroughfare(),
                                addressList.get(0).getFeatureName(), addressList.get(0).getPostalCode(), String.valueOf(latLng.latitude), String.valueOf(latLng.longitude));

                        mapPresenter.addMarker();
                        break;
                    }

                    try {
                        Thread.sleep(400);
                        t++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (t == 5) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (fragment.isVisible())
                                    onBackPressed();
                                Toast.makeText(MapActivity.this, "Keine Adresse für diesen Marker gefunden", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    }
                }
            }

        });
        t.start();
    }

    @Override
    public void onBackPressed() {

        if (!fragment.isVisible()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
}