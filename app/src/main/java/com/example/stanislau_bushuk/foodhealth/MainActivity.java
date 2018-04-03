package com.example.stanislau_bushuk.foodhealth;

import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.stanislau_bushuk.foodhealth.API.IAPI;
import com.example.stanislau_bushuk.foodhealth.Model.Recipes;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import butterknife.OnTouch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Inject
    IAPI iapi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent().inject(this);

        //Menu-------------------
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.search:
                      //iapi.getJson();
                        Request request=new Request();
                        request.getJson();
                        Request.getIapi().getJson().enqueue(new Callback<Recipes>() {
                            @Override
                            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                                Timber.e("true");
                                Timber.e(String.valueOf(response.body().getHits().size()));

                            }

                            @Override
                            public void onFailure(Call<Recipes> call, Throwable t) {
                                Timber.e("fail");
                                t.printStackTrace();

                            }
                        });
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
