package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;
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


    private Fragment searchFragment;
    private Fragment deepSearchFragment;
    private Fragment favoriteFragment;

    @ProvidePresenter
    public MainActivityPresenter createMainActivityPresenter() {
        return new MainActivityPresenter(router);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        App.getAppComponent().inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initContainers();
        if (savedInstanceState == null) {
            presenter.createNewChain(Constants.SEARCH_SCREEN);
            //navigator.applyCommands(new Command[]{new Replace(Constants.SEARCH_SCREEN, 1)});
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.search:
                        presenter.createNewChain(Constants.SEARCH_SCREEN);
                        item.setChecked(true);

                        break;
                    case R.id.search_deep:
                        presenter.createNewChain(Constants.DEEP_SEARCH_SCREEN);
                        item.setChecked(true);

                        break;
                    case R.id.featured:
                        presenter.createNewChain(Constants.FAVOURITE_SCREEN);
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


    private Navigator navigator = new Navigator() {


        @Override
        public void applyCommands(final Command[] commands) {
            // super.applyCommands(commands);
            for (final Command command : commands) apply(command);
        }

        void apply(final Command command) {
            if (command instanceof Replace) {
                final android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

                switch (((Replace) command).getScreenKey()) {
                    case Constants.SEARCH_SCREEN:

                        fm.beginTransaction()
                                .detach(deepSearchFragment)
                                .detach(favoriteFragment)
                                .attach(searchFragment)
                                .commitNow();
                        break;
                    case Constants.DEEP_SEARCH_SCREEN:
                        fm.beginTransaction()
                                .attach(deepSearchFragment)
                                .detach(searchFragment)
                                .detach(favoriteFragment)
                                .commitNow();
                        break;
                    case Constants.FAVOURITE_SCREEN:
                        fm.beginTransaction()
                                .attach(favoriteFragment)
                                .detach(searchFragment)
                                .detach(deepSearchFragment)
                                .commitNow();
                        break;
                }
            }else if(command instanceof Forward){
                Timber.e("FORWARD");
                final android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

                switch (((Forward) command).getScreenKey()) {
                    case Constants.SEARCH_SCREEN:
                        fm.beginTransaction()
                                .detach(deepSearchFragment)
                                .detach(favoriteFragment)
                                .attach(searchFragment)
                                .commitNow();
                        break;
                    case Constants.DEEP_SEARCH_SCREEN:
                        fm.beginTransaction()
                                .detach(searchFragment)
                                .detach(favoriteFragment)
                                .attach(deepSearchFragment)
                                .commitNow();
                        break;
                    case Constants.FAVOURITE_SCREEN:
                        fm.beginTransaction()
                                .detach(searchFragment)
                                .detach(deepSearchFragment)
                                .attach(favoriteFragment)
                                .commitNow();
                        break;
                }
            }
        }
    };

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initContainers() {
        final android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        searchFragment = fm.findFragmentByTag(Constants.SEARCH_SCREEN);
        if (searchFragment == null) {
            searchFragment = FragmentManager.getNewInstanceFragment(Constants.SEARCH_SCREEN);
            fm.beginTransaction()
                    .add(R.id.main_contener_frame_layout, searchFragment, Constants.SEARCH_SCREEN)
                    .detach(searchFragment).commitNow();
        }

        deepSearchFragment = fm.findFragmentByTag(Constants.DEEP_SEARCH_SCREEN);
        if (deepSearchFragment == null) {
            deepSearchFragment = FragmentManager.getNewInstanceFragment(Constants.DEEP_SEARCH_SCREEN);
            fm.beginTransaction()
                    .add(R.id.main_contener_frame_layout, deepSearchFragment, Constants.DEEP_SEARCH_SCREEN)
                    .detach(deepSearchFragment).commitNow();
        }

        favoriteFragment = fm.findFragmentByTag(Constants.FAVOURITE_SCREEN);
        if (favoriteFragment == null) {
            favoriteFragment = FragmentManager.getNewInstanceFragment(Constants.FAVOURITE_SCREEN);
            fm.beginTransaction()
                    .add(R.id.main_contener_frame_layout, favoriteFragment, Constants.FAVOURITE_SCREEN)
                    .detach(favoriteFragment).commitNow();
        }
    }
}
