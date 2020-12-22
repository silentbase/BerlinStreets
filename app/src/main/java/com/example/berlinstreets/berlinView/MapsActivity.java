package com.example.berlinstreets.berlinView;

import android.content.Intent;
import android.os.Bundle;

import com.example.berlinstreets.R;
import com.example.berlinstreets.berlinModul.Marker;
import com.example.berlinstreets.berlinPresenter.MapPresenter;
import com.example.berlinstreets.berlinView.ui.main.MapsFragment;
import com.example.berlinstreets.berlinView.ui.main.MarkerListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.ListFragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.example.berlinstreets.berlinView.ui.main.SectionsPagerAdapter;

public class MapsActivity extends AppCompatActivity implements ConfirmMarkerFragment.OnFragmentInteractionListener {

    private MapsFragment mapsFragment;
    private MarkerListFragment markerListFragment;

    private SectionsPagerAdapter sectionsPagerAdapter;
    private MapPresenter mapPresenter;

    private ConfirmMarkerFragment confirmMarkerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapPresenter = new MapPresenter(this);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, mapPresenter, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        mapPresenter.setData(mapPresenter.getSession().getUserData().get("Id"));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    /**
     * sends a request to delete a marker from the server
     * @param v
     */
    public void deleteMarker(View v){
        markerListFragment.removeItem(v);
    }

    /**
     * removes the item from the listview
     */
    public void deleteItem(){

    }

    public void setMapFragment(MapsFragment fragment) {
        Log.d("bebe", "setMapsFrag");
        this.mapsFragment = fragment;
    }

    public void setListFragment(MarkerListFragment fragment) {
        Log.d("bebe", "setListFrag");
        this.markerListFragment = fragment;
    }

    public void setListData(Marker... markers) {
        markerListFragment.setListData(markers);
    }

    public void setMarkers(Marker... markers) {
        mapsFragment.setMarkers(markers);
    }

    public void setConfirmMarkerFragment(ConfirmMarkerFragment confirmMarkerFragment) {
        this.confirmMarkerFragment = confirmMarkerFragment;
    }

    public void markerSucceeded() {
        (mapsFragment).markerSucceeded();
    }

    public void markerFailed() {

    }

    @Override
    public void onBackPressed() {

        if (!confirmMarkerFragment.isVisible()) {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        } else {
            mapsFragment.removeConfirmMarkerFrag();
        }
    }

    @Override
    public void onFragmentInteraction(String title) {
        (mapsFragment).addMarker(title);
    }
}