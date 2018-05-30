package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.presenters.DeepSearchPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.view.DeepSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class DeepSearchFragment extends MvpAppCompatFragment implements DeepSearchView {

    @BindView(R.id.to_deep_search_edit_text)
    EditText toEditText;

    @BindView(R.id.from_deep_search_edit_text)
    EditText fromEditText;

    @BindView(R.id.up_to_deep_search_edit_text)
    EditText upToEditText;

    @BindView(R.id.find_deep_search_button)
    Button findButton;

    @InjectPresenter(type = PresenterType.GLOBAL)
    DeepSearchPresenter presenter;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_deep_search, container, false);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.find_deep_search_button)
    public void onClick(final View v) {

        if (v == findButton) {
            presenter.goTo(Constants.DEEP_SEARCH_ACTIVITY);
            presenter.getRecipeFilter(fromEditText.getText().toString(), toEditText.getText().toString(), upToEditText.getText().toString());
        }

    }

    @OnCheckedChanged({R.id.vegan_deep_search_checkbox, R.id.vegetarian_deep_search_checkbox,
            R.id.sugar_conscious_deep_search_checkbox, R.id.peanut_free_deep_search_checkbox, R.id.tree_nut_free_deep_search_checkbox,
            R.id.alcohol_free_deep_search_checkbox
    })
    public void changedCheckHealth(final CompoundButton buttonView, final boolean isChecked) {
        presenter.setMap(buttonView, isChecked, Constants.HEALTH);
    }

    @OnCheckedChanged({R.id.balanced_deep_search_checkbox, R.id.high_protein_deep_search_checkbox, R.id.low_fat_deep_search_checkbox,
            R.id.low_carb_deep_search_checkbox})
    public void changedCheckDiet(final CompoundButton buttonView, final boolean isChecked) {
        presenter.setMap(buttonView, isChecked, Constants.DIET);
    }

    @Override
    public void showData(final ArrayList<Hits> recipes) {

    }

    @Override
    public void progressBarVisibility(final int visible) {

    }

    @Override
    public void notFound() {

    }

    @Override
    public void clearViewStateCommands() {

    }
}
