package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity {

    @BindView(R.id.main_navigation_bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    private FragmentTransaction fragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            fragment = getSupportFragmentManager().beginTransaction().replace(R.id.main_contener_frame_layout, new SearchFragment());
            fragment.commit();
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.search:
                        fragment = getSupportFragmentManager().beginTransaction().replace(R.id.main_contener_frame_layout, new SearchFragment());
                        fragment.commit();

                        break;
                    case R.id.search_deep:
                        break;
                    case R.id.featured:
                        fragment = getSupportFragmentManager().beginTransaction().replace(R.id.main_contener_frame_layout, new FavoriteFragment());
                        fragment.commit();

                        break;
                }

                return true;
            }
        });
    }

}
