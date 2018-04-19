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
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class FavoriteFragment extends MvpAppCompatFragment implements FavoriteView {
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
    }

    @Override
    public void showList(final RealmResults<Recipe> recipes) {
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        listRecyclerView.setLayoutManager(mLayoutManager);
        final FavoriteAdapter favoriteAdapter = new FavoriteAdapter(recipes, getContext());
        listRecyclerView.setAdapter(favoriteAdapter);
    }
}
