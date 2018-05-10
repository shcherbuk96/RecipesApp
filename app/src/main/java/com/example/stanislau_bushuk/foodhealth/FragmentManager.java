package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.DeepSearchFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.SearchFragment;

public class FragmentManager {

    private static final String STATUS = "KEY";
    public static Fragment getNewInstanceFragment(final String screenKey, final int data) {
        Fragment fragment = null;
        final Bundle arguments = new Bundle();
        arguments.putInt(STATUS, data);
        switch (screenKey) {
            case Constants.SEARCH_SCREEN: {
                fragment = new SearchFragment();
                fragment.setArguments(arguments);
                break;
            }
            case Constants.DEEP_SEARCH_SCREEN: {
                fragment = new DeepSearchFragment();
                fragment.setArguments(arguments);
                break;
            }
            case Constants.FAVOURITE_SCREEN: {
                fragment = new FavoriteFragment();
                fragment.setArguments(arguments);
                break;
            }
        }

        return fragment;
    }
}
