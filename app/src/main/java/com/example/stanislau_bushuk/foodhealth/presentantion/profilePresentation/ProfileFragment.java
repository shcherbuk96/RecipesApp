package com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.FragmentCreater;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.OwnRecipesFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.presenters.ProfilePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends MvpAppCompatFragment implements MvpView {

    @InjectPresenter
    ProfilePresenter profilePresenter;

    @Inject
    FragmentCreater fragmentCreater;

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

    private FavoriteFragment favoriteFragment = new FavoriteFragment();
    private OwnRecipesFragment ownRecipesFragment = new OwnRecipesFragment();


    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);


        GlideApp.with(this)
                .load(profilePresenter.getUser().getPhotoUrl())
                .error(R.drawable.ic_person_white)
                .centerCrop()
                .into(userPhoto);

        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        userEmail.setText(profilePresenter.getUser().getEmail());
        userName.setText(profilePresenter.getUser().getDisplayName());
        tabLayout.setTabTextColors(
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.white)
        );

        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(favoriteFragment, getString(R.string.profile_favourites_tab));
        viewPagerAdapter.addFragment(ownRecipesFragment, getString(R.string.profile_own_tab));
        pager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(pager);
    }
}
