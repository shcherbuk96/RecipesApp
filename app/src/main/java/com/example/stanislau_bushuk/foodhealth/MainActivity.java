package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MvpView {

    @BindView(R.id.main_navigation_bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    @Inject
    Router router;

    @Inject
    NavigatorHolder navigatorHolder;

    @InjectPresenter
    MainActivityPresenter presenter;

    @Inject
    NavigationUtil navigationUtil(){
        return new NavigationUtil(this);
    }

//    NavigationUtil navigationUtil(){
//        if(navigationUtil==null) {
//            this.navigationUtil = new NavigationUtil(fragmentActivity);
//        }
//        return navigationUtil;
//    }





//    @Override
//    public void onBackPressed() {
//
//        if (getSupportFragmentManager().getFragments().contains(searchFragment)) {
//            super.onBackPressed();
//        } else {
//            final MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.search);
//            menuItem.setChecked(true);
//            presenter.goBack(Constants.SEARCH_SCREEN);
//        }
//
//    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getAppComponent().inject(this);
        router.replaceScreen(Constants.SEARCH_SCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.search:
                        presenter.goBack(Constants.SEARCH_SCREEN);
                        item.setChecked(true);

                        break;
                    case R.id.search_deep:
                        presenter.goTo(Constants.DEEP_SEARCH_SCREEN);
                        item.setChecked(true);

                        break;
                    case R.id.featured:
                        presenter.goTo(Constants.FAVOURITE_SCREEN);
                        item.setChecked(true);

                        break;
                }

                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        navigatorHolder.setNavigator(navigationUtil());
    }

    @Override
    protected void onPause() {
        super.onPause();

        navigatorHolder.removeNavigator();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
