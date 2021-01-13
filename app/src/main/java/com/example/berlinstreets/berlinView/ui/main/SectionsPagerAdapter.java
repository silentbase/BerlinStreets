package com.example.berlinstreets.berlinView.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.ListFragment;

import com.example.berlinstreets.R;
import com.example.berlinstreets.berlinPresenter.MapPresenter;
import com.example.berlinstreets.berlinView.MapsActivity;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Fragment fragment;
    private MapPresenter mapPresenter;
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, MapPresenter mapPresenter, FragmentManager fm) {
        super(fm);
        mContext = context;
        this.mapPresenter = mapPresenter;
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                fragment = MapsFragment.newInstance();
                ((MapsActivity) mContext).setMapFragment((MapsFragment) fragment);
                break;
            case 1:
                if (mapPresenter.getSession().isLoggedIn()) {
                    fragment = MarkerListFragment.newInstance("", "");
                    ((MapsActivity) mContext).setListFragment((MarkerListFragment) fragment);
                } else {
                    fragment = EmptyListFragment.newInstance("", "");
                }
                break;
        }

        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    public Fragment getFragment() {
        return fragment;
    }
}