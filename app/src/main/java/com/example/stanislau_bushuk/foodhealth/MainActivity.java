package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.DeepSearchFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity {

    @BindView(R.id.main_navigation_bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.main_contener_frame_layout, new SearchFragment());
            fragmentTransaction.commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_contener_frame_layout, new SearchFragment()).commit();

                        break;
                    case R.id.search_deep:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_contener_frame_layout, new DeepSearchFragment()).commit();

                        break;
                    case R.id.featured:
                        break;
                }

                return true;
            }
        });
    }
}
