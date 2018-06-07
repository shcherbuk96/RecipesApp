package com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.MainActivity;
import com.example.stanislau_bushuk.foodhealth.NavigationUtil;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import ru.terrakok.cicerone.NavigatorHolder;

public class FavoriteFragment extends MvpAppCompatFragment implements FavoriteView, FavoriteAdapter.Listener {

    FavoriteAdapter favoriteAdapter;

    @BindView(R.id.favorite_list_recycler_view)
    RecyclerView listRecyclerView;

    @InjectPresenter
    FavoritePresenter favoritePresenter;

    @Inject
    NavigatorHolder navigatorHolder;


    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        App.getAppComponent().inject(this);

        ButterKnife.bind(this, view);

        final Toolbar toolbar=view.findViewById(R.id.favorite_toolbar);
        toolbar.setTitle(R.string.favorite_toolbar);

        listRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favoriteAdapter = new FavoriteAdapter(new ArrayList<Recipe>(), this);
        final DividerItemDecoration itemDecorator = new DividerItemDecoration(listRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getResources().getDrawable(R.drawable.devider));
        listRecyclerView.addItemDecoration(itemDecorator);
        listRecyclerView.setAdapter(favoriteAdapter);
        favoritePresenter.dataRealm();
    }

    @Override
    public void showList(final RealmResults<Recipe> recipes) {
        favoriteAdapter.updateAdapter(recipes);
    }

    @Override
    public void onItemClick(final String uri) {
        favoritePresenter.goTo(Constants.CARD_ACTIVITY,uri);
    }

    @Override
    public void deleteFromFavorite(final Recipe recipe) {
        favoritePresenter.deleteFromFavorite(recipe);
    }

}
