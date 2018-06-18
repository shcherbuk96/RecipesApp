package com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.presenters.ProfilePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnonimProfileFragment extends MvpAppCompatFragment implements ProfileView {

    @BindView(R.id.profile_sign_in_button)
    Button signInButton;

    @BindView(R.id.profile_registration)
    Button registrButton;

    @InjectPresenter
    ProfilePresenter profilePresenter;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_no_registr_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.profile_sign_in_button)
    public void signIn() {
        profilePresenter.signIn();
    }

    @OnClick(R.id.profile_registration)
    public void registr() {
        profilePresenter.register();
    }

    @Override
    public void onResume() {
        super.onResume();

        profilePresenter.checkAuth();
    }

    @Override
    public void showPhotoUser(String uri) {

    }
}
