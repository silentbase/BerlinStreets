package com.example.berlinstreets.berlinView.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.berlinstreets.R;
import com.example.berlinstreets.berlinPresenter.MapPresenter;
import com.example.berlinstreets.berlinView.ConfirmMarkerFragment;
import com.example.berlinstreets.berlinView.LoginActivity;
import com.example.berlinstreets.berlinView.MapsActivity;
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

public class MapsFragment extends Fragment {

    private Context mapContext;

    private MapPresenter mapPresenter;
    private GoogleMap mMap;
    private Marker marker;
    private MarkerOptions markerOptions;
    private ConfirmMarkerFragment confirmMarkerFragment;

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

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(52.5198378, 13.4045256)));
            mMap.setMinZoomPreference(10.5f);

            // Add a marker in Sydney and move the camera
            markerOptions = new MarkerOptions();

        /*com.example.berlinstreets.berlinModul.Marker[] markers = new com.example.berlinstreets.berlinModul.Marker[1];
        markers[0] = new com.example.berlinstreets.berlinModul.Marker("","","","","","21","76",this); */
            mapPresenter.getAllMarkers();
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
                                ((MapsActivity) mapContext).runOnUiThread(new Runnable() {
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
                        startActivity(new Intent(mapContext, LoginActivity.class));
                    } else {
                        //geoCoder is in a separate thread, due to its long execution time
                        geoCoderThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);

                                setLatLng(latLng);

                                addressList = new ArrayList<>();
                                Geocoder geocoder = new Geocoder(mapContext, Locale.getDefault());

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
    };


    public void addMarker(final String title) {
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
                        ((MapsActivity) mapContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                removeConfirmMarkerFrag();
                                Toast.makeText(mapContext, "Keine Adresse für diesen Marker gefunden", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    }
                }
            }

        });
        t.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        confirmMarkerFragment = ConfirmMarkerFragment.newInstance();
        ((MapsActivity) mapContext).setConfirmMarkerFragment(confirmMarkerFragment);

        mapPresenter = new MapPresenter(mapContext);
        mapPresenter.setData(mapPresenter.getSession().getUserData().get("Id"));

    }

    public static MapsFragment newInstance() {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.mapContext = context;
        super.onAttach(context);
    }

    public void setMarkers(com.example.berlinstreets.berlinModul.Marker... m) {
        markers = m;
    }

    public com.example.berlinstreets.berlinModul.Marker[] getMarkers() {
        return markers;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void confirmMarker() {
        getChildFragmentManager().beginTransaction().replace(R.id.mapFragment, confirmMarkerFragment).commit();
    }

    public void markerSucceeded() {
        marker = mMap.addMarker(markerOptions);
        removeConfirmMarkerFrag();
    }

    public void removeConfirmMarkerFrag() {
        getChildFragmentManager().beginTransaction().remove(confirmMarkerFragment).commit();
    }
}