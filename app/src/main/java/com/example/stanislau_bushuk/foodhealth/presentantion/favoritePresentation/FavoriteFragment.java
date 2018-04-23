package com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.ActivityManager;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class FavoriteFragment extends MvpAppCompatFragment implements FavoriteView, CallBackFavorite, CallBackActivity {

    FavoriteAdapter favoriteAdapter;

    @BindView(R.id.favorite_list_recycler_view)
    RecyclerView listRecyclerView;

    @InjectPresenter
    FavoritePresenter favoritePresenter;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        final List<Recipe> list = new ArrayList<>();
        listRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favoriteAdapter = new FavoriteAdapter(this, this, list, getContext());
        listRecyclerView.setAdapter(favoriteAdapter);
    }

    @Override
    public void showList(final RealmResults<Recipe> recipes) {
        favoriteAdapter.updateAdapter(recipes);
    }

    @Override
    public void addToRealm(final Recipe recipe) {
        favoritePresenter.addToRealm(recipe);
    }

    @Override
    public void deleteFromRealm(final Recipe recipe) {
        favoritePresenter.deleteFromRealm(recipe);
    }

    @Override
    public void getInfo(final String uri) {
        ActivityManager.startCardActivity(getActivity(), uri);
    }

}
