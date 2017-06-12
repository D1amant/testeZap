package com.example.luis.testeIlegra.Fragments;

/**
 * Created by luis on 27/04/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.luis.testeIlegra.Entities.Property;
import com.example.luis.testeIlegra.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public  class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private Property properties;

    public PlaceholderFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber , Property properties) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        fragment.setPropertyes( properties);
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.img);
        try {
            Picasso.with(getContext()).load(properties.getUrlImageGalery()
                    .getString(getArguments().getInt(ARG_SECTION_NUMBER)))
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .fit()
                    .into(imageView);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return rootView;
    }

    private void setPropertyes(Property properties){
        this.properties = properties;
    }
}
