package com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.presenters.OwnRecipesPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.view.OwnRecipesView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OwnRecipesFragment extends MvpAppCompatFragment implements OwnRecipesView, RecyclerViewOwnRecipesAdapter.Listener {

    @InjectPresenter
    OwnRecipesPresenter presenter;

    @BindView(R.id.recycler_view_own_recipes)
    RecyclerView recyclerView;

    @BindView(R.id.profile_add_recipe_floating_button)
    FloatingActionButton floatingActionButton;

    RecyclerViewOwnRecipesAdapter recyclerAdapter;

    private final List<OwnRecipe> ownRecipes = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_own_recipes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerAdapter = new RecyclerViewOwnRecipesAdapter(this, ownRecipes);
        final DividerItemDecoration itemDecorator = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getResources().getDrawable(R.drawable.devider));
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, final int newState) {
                if (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() > 10 && floatingActionButton.isShown()) {
                    floatingActionButton.hide();
                } else if (!floatingActionButton.isShown()) {
                    floatingActionButton.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    @Override
    public void onItemClick(final String name) {
        presenter.navigateTo(Constants.CARD_OWN_RECIPE,name);
    }

    @OnClick(R.id.profile_add_recipe_floating_button)
    public void clickFloatButton() {
        presenter.goTo();
    }


    @Override
    public void showRecipes(final List<OwnRecipe> ownRecipes) {
        recyclerAdapter.updateAdapter(ownRecipes);
    }

    @Override
    public void addRecipe(final OwnRecipe ownRecipe) {
        recyclerAdapter.addRecipe(ownRecipe);
    }

}
