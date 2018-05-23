package com.example.stanislau_bushuk.foodhealth;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import javax.inject.Singleton;

import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import timber.log.Timber;



public class NavigationUtil extends SupportAppNavigator {


    public NavigationUtil(final Context context) {
        super((FragmentActivity) context, R.id.main_contener_frame_layout);
    }

    @Override
    public void applyCommands(final Command[] commands) {
        Timber.e("NAVIGATOR");
        super.applyCommands(commands);
    }


    @Override
    protected Intent createActivityIntent(final Context context, final String screenKey, final Object data) {
        return null;
    }

    @Override
    protected Fragment createFragment(final String screenKey, final Object data) {
        return FragmentCreater.getNewInstanceFragment(screenKey);
    }

}










/* protected void applyCommand(final Command command) {
        final FragmentManager fm = context.getSupportFragmentManager();

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
            //  Toast.makeText(MainActivity.this, ((SystemMessage) command).getMessage(), Toast.LENGTH_SHORT).show();
        } else if (command instanceof Forward) {

            switch (((Forward) command).getScreenKey()) {
                case Constants.SEARCH_SCREEN: {
                    fm.beginTransaction()
                            .detach(deepSearchFragment)
                            .detach(favoriteFragment)
                            .attach(searchFragment)
                            .commit();

                    break;
                }

                case Constants.DEEP_SEARCH_SCREEN: {
                    fm.beginTransaction()
                            .detach(searchFragment)
                            .detach(favoriteFragment)
                            .attach(deepSearchFragment)
                            .commit();

                    break;
                }

                case Constants.FAVOURITE_SCREEN: {
                    fm.beginTransaction()
                            .detach(searchFragment)
                            .detach(deepSearchFragment)
                            .attach(favoriteFragment)
                            .commit();

                    break;
                }

                case Constants.CARD_ACTIVITY: {
                    ActivityManager.startCardActivity(context, ((Forward) command)
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

    }*/



   /* private void initContainers() {
        final FragmentManager fm = context.getSupportFragmentManager();
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
    }*/