package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.stanislau_bushuk.foodhealth.API.IAPI;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.SearchFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Inject
    IAPI iapi;


    public MainActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent().inject(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.contener, new SearchFragment());
        fragmentTransaction.commit();
        //Menu-------------------
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.search:

                        //selectedFragment = ItemOneFragment.newInstance();
                        break;
                    case R.id.search_deep:
                        //selectedFragment = ItemTwoFragment.newInstance();
                        break;
                    case R.id.featured:
                        //selectedFragment = ItemThreeFragment.newInstance();
                        break;
                }
/*                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();*/
                return true;
            }
        });

        //-------------------
    }
}
