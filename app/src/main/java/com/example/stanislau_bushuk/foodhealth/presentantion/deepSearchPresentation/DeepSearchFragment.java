package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.stanislau_bushuk.foodhealth.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeepSearchFragment extends MvpAppCompatFragment {

    @BindView(R.id.balanced_deep_search_checkbox)
    AppCompatCheckBox balancedCheckbox;

    @BindView(R.id.high_protein_deep_search_checkbox)
    AppCompatCheckBox highProteinCheckbox;

    @BindView(R.id.low_fat_deep_search_checkbox)
    AppCompatCheckBox lowFatCheckbox;

    @BindView(R.id.low_carb_deep_search_checkbox)
    AppCompatCheckBox lowCarbCheckbox;

    @BindView(R.id.vegan_deep_search_checkbox)
    AppCompatCheckBox veganCheckbox;

    @BindView(R.id.vegetarian_deep_search_checkbox)
    AppCompatCheckBox vegetarianCheckbox;

    @BindView(R.id.sugar_conscious_deep_search_checkbox)
    AppCompatCheckBox sugarCheckbox;

    @BindView(R.id.peanut_free_deep_search_checkbox)
    AppCompatCheckBox peanutCheckbox;

    @BindView(R.id.tree_nut_free_deep_search_checkbox)
    AppCompatCheckBox treeCheckbox;

    @BindView(R.id.alcohol_free_deep_search_checkbox)
    AppCompatCheckBox alcoholCheckbox;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deep_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //
        ButterKnife.bind(this,view);
    }
}
