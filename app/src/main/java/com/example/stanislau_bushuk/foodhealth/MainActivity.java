package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.DeepSearchFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.SearchFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;
import ru.terrakok.cicerone.commands.SystemMessage;

public class MainActivity extends MvpAppCompatActivity implements MvpView {

    @BindView(R.id.main_navigation_bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    @Inject
    Router router;

    @Inject
    NavigatorHolder navigatorHolder;

    @InjectPresenter
    MainActivityPresenter presenter;

    private SearchFragment searchFragment;
    private DeepSearchFragment deepSearchFragment;
    private FavoriteFragment favoriteFragment;

    private final Navigator navigator = new Navigator() {

        @Override
        public void applyCommands(final Command[] commands) {
            for (final Command command : commands) applyCommand(command);
        }

        void applyCommand(final Command command) {
            final FragmentManager fm = getSupportFragmentManager();

            if (command instanceof BackTo) {
                final Bundle bundle = new Bundle();
                bundle.putInt(Constants.KEY_FRAGMENT, 1);
                searchFragment.setArguments(bundle);
                fm.beginTransaction()
                        .detach(deepSearchFragment)
                        .detach(favoriteFragment)
                        .attach(searchFragment)
                        .commitNow();

            } else if (command instanceof SystemMessage) {
                Toast.makeText(MainActivity.this, ((SystemMessage) command).getMessage(), Toast.LENGTH_SHORT).show();
            } else if (command instanceof Forward) {

                switch (((Forward) command).getScreenKey()) {
                    case Constants.SEARCH_SCREEN: {
                        fm.beginTransaction()
                                .detach(deepSearchFragment)
                                .detach(favoriteFragment)
                                .attach(searchFragment)
                                .commitNow();

                        break;
                    }

                    case Constants.DEEP_SEARCH_SCREEN: {
                        fm.beginTransaction()
                                .detach(searchFragment)
                                .detach(favoriteFragment)
                                .attach(deepSearchFragment)
                                .commitNow();

                        break;
                    }

                    case Constants.FAVOURITE_SCREEN: {
                        fm.beginTransaction()
                                .detach(searchFragment)
                                .detach(deepSearchFragment)
                                .attach(favoriteFragment)
                                .commitNow();

                        break;
                    }

                    case Constants.CARD_ACTIVITY: {
                        ActivityManager.startCardActivity(MainActivity.this, ((Forward) command)
                                .getTransitionData().toString());
                    }
                }

            } else if (command instanceof Replace) {

                switch (((Replace) command).getScreenKey()) {
                    case Constants.SEARCH_SCREEN:
                        final Bundle bundle = new Bundle();
                        bundle.putInt(Constants.KEY_FRAGMENT, 0);
                        searchFragment.setArguments(bundle);
                        fm.beginTransaction()
                                .detach(deepSearchFragment)
                                .detach(favoriteFragment)
                                .attach(searchFragment)
                                .commitNow();

                        break;
                }
            }

        }
    };

    private void initContainers() {
        final FragmentManager fm = getSupportFragmentManager();
        searchFragment = (SearchFragment) fm.findFragmentByTag(Constants.SEARCH_SCREEN);

        if (searchFragment == null) {
            searchFragment = (SearchFragment) FragmentCreater.getNewInstanceFragment(Constants.SEARCH_SCREEN);
            fm.beginTransaction()
                    .add(R.id.main_contener_frame_layout, searchFragment, Constants.SEARCH_SCREEN)
                    .detach(searchFragment).commitNow();
        }

        deepSearchFragment = (DeepSearchFragment) fm.findFragmentByTag(Constants.DEEP_SEARCH_SCREEN);

        if (deepSearchFragment == null) {
            deepSearchFragment = (DeepSearchFragment) FragmentCreater.getNewInstanceFragment(Constants.DEEP_SEARCH_SCREEN);
            fm.beginTransaction()
                    .add(R.id.main_contener_frame_layout, deepSearchFragment, Constants.DEEP_SEARCH_SCREEN)
                    .detach(deepSearchFragment).commitNow();
        }

        favoriteFragment = (FavoriteFragment) fm.findFragmentByTag(Constants.FAVOURITE_SCREEN);

        if (favoriteFragment == null) {
            favoriteFragment = (FavoriteFragment) FragmentCreater.getNewInstanceFragment(Constants.FAVOURITE_SCREEN);
            fm.beginTransaction()
                    .add(R.id.main_contener_frame_layout, favoriteFragment, Constants.FAVOURITE_SCREEN)
                    .detach(favoriteFragment).commitNow();
        }
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().contains(searchFragment)) {
            super.onBackPressed();
        } else {
            final MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.search);
            menuItem.setChecked(true);
            presenter.goBack(Constants.SEARCH_SCREEN);
        }

    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        App.getAppComponent().inject(this);

        super.onCreate(savedInstanceState);

        initContainers();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            navigator.applyCommands(new Command[]{new Replace(Constants.SEARCH_SCREEN, 0)});
        }

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

        navigatorHolder.setNavigator(navigator);
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
