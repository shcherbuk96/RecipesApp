package com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.presenters.OwnRecipesPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.view.OwnRecipesView;

import java.util.ArrayList;

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

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_own_recipes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);
        recyclerAdapter = new RecyclerViewOwnRecipesAdapter(this, new ArrayList<OwnRecipe>());
        final DividerItemDecoration itemDecorator = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getResources().getDrawable(R.drawable.devider));
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public void onItemClick(final String uri) {

    }

    @OnClick(R.id.profile_add_recipe_floating_button)
    public void clickFloatButton(){
        presenter.goTo();
    }


}
