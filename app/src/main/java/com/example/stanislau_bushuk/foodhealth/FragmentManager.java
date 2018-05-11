package com.example.stanislau_bushuk.foodhealth;

import android.support.v4.app.Fragment;

import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.DeepSearchFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.SearchFragment;

public class FragmentManager {


    public static Fragment getNewInstanceFragment(final String screenKey) {
        Fragment fragment = null;

        switch (screenKey) {
            case Constants.SEARCH_SCREEN: {
                fragment = new SearchFragment();

                break;
            }
            case Constants.DEEP_SEARCH_SCREEN: {
                fragment = new DeepSearchFragment();

                break;
            }
            case Constants.FAVOURITE_SCREEN: {
                fragment = new FavoriteFragment();

                break;
            }
        }

        return fragment;
    }
}
