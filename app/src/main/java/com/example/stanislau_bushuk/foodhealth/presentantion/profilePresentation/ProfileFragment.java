package com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.FragmentCreater;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.OwnRecipesFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.presenters.ProfilePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.example.stanislau_bushuk.foodhealth.Constants.RESULT_LOAD_IMAGE;

public class ProfileFragment extends MvpAppCompatFragment implements ProfileView {

    private final FavoriteFragment favoriteFragment = new FavoriteFragment();
    private final OwnRecipesFragment ownRecipesFragment = new OwnRecipesFragment();
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
    @BindView(R.id.profile_progressBar)
    ProgressBar progressBar;
    FirebaseUser firebaseUser;

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

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

//        GlideApp.with(this)
//                .load(profilePresenter.getUser().getPhotoUrl())
//                .error(R.drawable.ic_person_white)
//                .centerCrop()
//                .into(userPhoto);
        profilePresenter.getUserPhoto(firebaseUser.getUid());
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

    @OnClick(R.id.profile_user_photo)
    public void loadPhoto() {
        Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(gallery, RESULT_LOAD_IMAGE);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            userPhoto.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            profilePresenter.loadImage(data.getData());
        }
    }


    @Override
    public void showPhotoUser(String url) {
        GlideApp.with(this)
                .asBitmap()
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable final GlideException e, final Object model, final Target<Bitmap> target, final boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(final Bitmap resource, final Object model, final Target<Bitmap> target, final DataSource dataSource, final boolean isFirstResource) {
                        ProfileFragment.this.progressBar.setVisibility(View.INVISIBLE);
                        ProfileFragment.this.userPhoto.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .placeholder(R.drawable.ic_person_black_24dp)
                .error(R.drawable.ic_person_black_24dp)
                .load(url)
                .centerCrop()
                .into(userPhoto);
    }
}
