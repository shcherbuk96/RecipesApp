package com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.presenters.ProfilePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.view.ProfileView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends MvpAppCompatFragment implements ProfileView {


    @InjectPresenter
    ProfilePresenter profilePresenter;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
