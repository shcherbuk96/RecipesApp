package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.DeepSearchFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.AnonimProfileFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.ProfileFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.SearchFragment;

public class FragmentCreater {

    private SearchFragment searchFragment;
    private DeepSearchFragment deepSearchFragment;
    private FavoriteFragment favoriteFragment;
    private AnonimProfileFragment anonimProfileFragment;
    private ProfileFragment profileFragment;

    public Fragment getNewInstanceFragment(final String screenKey, final int data) {
        final Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_FRAGMENT,data);

        switch (screenKey) {
            case Constants.SEARCH_SCREEN: {

                if (this.searchFragment == null) {
                    searchFragment = new SearchFragment();
                }
                searchFragment.setArguments(bundle);
                return searchFragment;

            }

            case Constants.DEEP_SEARCH_SCREEN: {

                if (this.deepSearchFragment == null) {
                    deepSearchFragment = new DeepSearchFragment();
                }
                deepSearchFragment.setArguments(bundle);
                return deepSearchFragment;
            }

            case Constants.FAVOURITE_SCREEN: {

                if (this.favoriteFragment == null) {
                    favoriteFragment = new FavoriteFragment();
                }

                favoriteFragment.setArguments(bundle);
                return favoriteFragment;
            }

            case Constants.PROFILE_ANONIM_SCREEN:{

                if(this.anonimProfileFragment ==null){
                    anonimProfileFragment =new AnonimProfileFragment();
                }

                anonimProfileFragment.setArguments(bundle);
                return anonimProfileFragment;
            }

            case Constants.PROFILE_SCREEN:{

                if(this.profileFragment ==null){
                    profileFragment =new ProfileFragment();
                }

                profileFragment.setArguments(bundle);
                return profileFragment;
            }

            default: {
                return null;
            }

        }
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }
}
