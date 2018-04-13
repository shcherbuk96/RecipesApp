package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CardActivity extends MvpAppCompatActivity implements CardView {

    @BindView(R.id.card_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.card_servings_edit_text)
    EditText servingsEditText;

    @BindView(R.id.card_photo_image_view)
    ImageView photoView;

    @BindView(R.id.card_calories_text_view)
    TextView caloriesView;

    @BindView(R.id.card_dayly_text_view)
    TextView daylyView;

    @BindView(R.id.card_fat_text_view)
    TextView fatView;

    @BindView(R.id.card_carbs_text_view)
    TextView carbsView;

    @BindView(R.id.card_protein_text_view)
    TextView proteinView;

    @InjectPresenter
    CardPresenter presenter;

    private CardAdapter cardAdapter;
    private ArrayList<String> recipe;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);

        recipe=new ArrayList<>();

        initAdapter(recipe);

        final Intent i = getIntent();

        final String uri = i.getStringExtra(Constants.RECIPE_INTENT_KEY);
        Timber.e(uri);
        presenter.getRecipeFromUri(uri);
    }

    @Override
    public void showList(final Recipe recipe) {
        Timber.e(String.valueOf(recipe));

        setTitle(recipe.getLabel());

        GlideApp
                .with(this)
                .load(recipe.getImage())
                .centerCrop()
                .into(photoView);

        caloriesView.setText(getString(R.string.card_number_calories, recipe.getCalories()));
        daylyView.setText(getString(R.string.card_number_daily, recipe.getTotalDaily().getENERC_KCAL().getQuantity()));
        servingsEditText.setText(String.valueOf(recipe.getYield()));
        fatView.setText(getString(R.string.card_number_fat, recipe.getTotalNutrients().getFAT().getQuantity()));
        proteinView.setText(getString(R.string.card_number_protein, recipe.getTotalNutrients().getPROCNT().getQuantity()));
        carbsView.setText(getString(R.string.card_number_carbs, recipe.getTotalNutrients().getCHOCDF().getQuantity()));

        this.recipe.clear();
        this.recipe.addAll(recipe.getIngredientLines());
        cardAdapter.notifyDataSetChanged();
    }

    private void initAdapter(final ArrayList<String> recipe) {
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardAdapter = new CardAdapter(recipe, this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(cardAdapter);
    }
}
