package com.example.berlinstreets.berlinView.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.berlinstreets.R;
import com.example.berlinstreets.berlinModul.Marker;
import com.example.berlinstreets.berlinModul.SessionManager;
import com.example.berlinstreets.berlinPresenter.MapPresenter;
import com.example.berlinstreets.berlinView.MapsActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MarkerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarkerListFragment extends Fragment {

    private MapPresenter mapPresenter;

    private Context mapContext;
    private MyAdapter myAdapter;

    private Marker[] markers;
    private ArrayList<String> titles;
    private ArrayList<String> addresses;

    private ImageView deleteButton;
    private ListView listView;

    private SwipeRefreshLayout swipeRefreshLayout;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MarkerListFragment newInstance(String param1, String param2) {
        MarkerListFragment fragment = new MarkerListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MarkerListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.mapContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.marker_fragment_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.markerListViewId);
        deleteButton = view.findViewById(R.id.markerDeleteButtonId);

        mapPresenter = new MapPresenter(mapContext);

        mapPresenter.setData(mapPresenter.getSession().getUserData().get("Id"));

        mapPresenter.getUserMarkers();

    }

    public void removeItem(View v) {
        mapPresenter.setData(markers[Integer.parseInt(v.getTag().toString())].get_Id());
        mapPresenter.deleteMarker();

        titles.remove(Integer.parseInt(v.getTag().toString()));
        addresses.remove(Integer.parseInt(v.getTag().toString()));
        myAdapter.notifyDataSetChanged();
    }

    public void setListData(final Marker... markers) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                MarkerListFragment.this.markers = markers;
                titles = new ArrayList<>();
                addresses = new ArrayList<>();

                for (int i = 0; i < markers.length; i++) {
                    titles.add(markers[i].getTitle());
                    addresses.add(markers[i].getStreet() + " " + markers[i].getHouseNumber() + System.lineSeparator() + markers[i].getPostalcode());
                }

                myAdapter = new MyAdapter(mapContext, titles, addresses, deleteButton);
                ((MapsActivity) mapContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(myAdapter);
                    }
                });

                Log.d("bebe", "hallo zsm");
            }
        }).start();
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> aTitles;
        ArrayList<String> aAddresses;
        ImageView deleteButton;

        MyAdapter(Context context,
                  ArrayList title,
                  ArrayList addresses, ImageView deleteButton) {
            super(context, R.layout.marker_fragment_item, title);
            this.context = context;
            this.aTitles = title;
            this.aAddresses = addresses;
            this.deleteButton = deleteButton;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(mapContext.LAYOUT_INFLATER_SERVICE);
            View horizontal = layoutInflater.inflate(R.layout.marker_fragment_item, parent, false);

            //setBackground colour
            //horizontal.setBackgroundColor(3);

            TextView titles = horizontal.findViewById(R.id.listTitleId);
            TextView addresses = horizontal.findViewById(R.id.listAddressId);
            ImageView xButton = horizontal.findViewById(R.id.markerDeleteButtonId);

            xButton.setTag(position);
            titles.setText(aTitles.get(position));
            addresses.setText(aAddresses.get(position));

            return horizontal;
        }


    }
}
