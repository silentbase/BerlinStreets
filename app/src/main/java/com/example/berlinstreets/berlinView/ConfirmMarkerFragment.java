package com.example.berlinstreets.berlinView;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.berlinstreets.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmMarkerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmMarkerFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Button markerButton;
    private ProgressBar progressBarMarker;
    private Spinner spinner;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mapContext;
    private String selectedSpinnerItem;

    public ConfirmMarkerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConfirmMarkerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfirmMarkerFragment newInstance() {
        ConfirmMarkerFragment fragment = new ConfirmMarkerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mapContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement onFragmentInteractionListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_marker, container, false);
        markerButton = view.findViewById(R.id.confirmMarkerButton);
        progressBarMarker = view.findViewById(R.id.progressBarMarker);

        spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mapContext, R.array.markerTitles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        markerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBarMarker.setVisibility(View.VISIBLE);
                markerButton.setVisibility(View.INVISIBLE);
                sendBack(selectedSpinnerItem);
            }
        });

        return view;

    }

    private void sendBack(String title) {
        //getFragmentManager().beginTransaction().remove(this).commit();
        if (mListener != null)
            mListener.onFragmentInteraction(title);
       // ((MapActivity) mListener)

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedSpinnerItem = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
    }
}