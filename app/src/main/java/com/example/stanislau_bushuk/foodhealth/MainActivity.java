package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.stanislau_bushuk.foodhealth.modul.CiceroneModul;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.DeepSearchFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.SearchFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class MainActivity extends MvpAppCompatActivity {

    @BindView(R.id.main_navigation_bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    private FragmentTransaction fragment;

    @Inject
    CiceroneModul ciceroneModul;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            fragment = getSupportFragmentManager().beginTransaction().replace(R.id.main_contener_frame_layout, new SearchFragment());
            fragment.commit();
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent().inject(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.search:

                        fragment = getSupportFragmentManager().beginTransaction().replace(R.id.main_contener_frame_layout, new SearchFragment());
                        fragment.commit();

                        break;
                    case R.id.search_deep:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_contener_frame_layout, new DeepSearchFragment()).commit();

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

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(),R.id.main_contener_frame_layout) {
        @Override
        protected Fragment createFragment(final String screenKey, final Object data) {
            return null;
        }

        @Override
        protected void showSystemMessage(final String message) {

        }

        @Override
        protected void exit() {

        }
    };

}
