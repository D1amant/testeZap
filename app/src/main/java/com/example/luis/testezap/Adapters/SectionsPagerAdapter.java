package com.example.luis.testezap.Adapters;

/**
 * Created by luis on 27/04/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.luis.testezap.Entities.Property;
import com.example.luis.testezap.Fragments.PlaceholderFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    Property property;
    public SectionsPagerAdapter(FragmentManager fm , Property property )
    {
        super(fm);
        this.property = property;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1 , property);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}