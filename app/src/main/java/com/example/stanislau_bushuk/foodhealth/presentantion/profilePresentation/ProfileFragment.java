package com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.FragmentCreater;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.presenters.ProfilePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.view.ProfileView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends MvpAppCompatFragment implements ProfileView {


    @InjectPresenter
    ProfilePresenter profilePresenter;

    @BindView(R.id.profile_blur)
    RelativeLayout relativeLayout;

    @BindView(R.id.profile_user_photo)
    ImageView userPhoto;

    @BindView(R.id.profile_email)
    TextView userEmail;

    @BindView(R.id.profile_name)
    TextView userName;

    @BindView(R.id.profile_tabs)
    TabLayout tabLayout;

    @BindView(R.id.profile_pager)
    ViewPager pager;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        GlideApp.with(this)
                .load(profilePresenter.getUserPhoto())
                .error(R.drawable.ic_person_white)
                .centerCrop()
                .into(userPhoto);

        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        userEmail.setText(profilePresenter.getUserEmail());
        userName.setText(profilePresenter.getUserName());
        tabLayout.setTabTextColors(
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.white)
        );
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.addFragment(new FavoriteFragment(),"FAVOURITES");
        pager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(pager);
    }

}
