package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.NavigatorHolder;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MvpView {

    @BindView(R.id.main_navigation_bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    @Inject
    OwnRouter router;

    @Inject
    NavigatorHolder navigatorHolder;

    @InjectPresenter
    MainActivityPresenter presenter;

    private boolean instance = false;


    @Inject
    NavigationUtil navigationUtil() {
        return new NavigationUtil(this);
    }


    @Override
    public void onBackPressed() {
        final MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.search);
        menuItem.setChecked(true);
        presenter.goBack(Constants.SEARCH_SCREEN, -1);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.search:
                        presenter.goBack(Constants.SEARCH_SCREEN, 1);
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

        router.replaceScreen(Constants.SEARCH_SCREEN, 0);


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
