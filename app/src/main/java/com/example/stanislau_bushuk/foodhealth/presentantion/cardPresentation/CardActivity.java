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
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;

import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("");
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);

        Intent i = getIntent();

        String uri = i.getStringExtra("recipe");
        Timber.e(uri);
        presenter.getRecipeFromUri(uri);
    }

    @Override
    public void showList(final List<Recipe> recipe) {
        Recipe r = recipe.get(0);
        Timber.e(String.valueOf(r));

        setTitle(r.getLabel());

        GlideApp
                .with(this)
                .load(r.getImage())
                .centerCrop()
                .into(photoView);

        String caloriesQuantity = String.format("%.1f", r.getCalories());
        caloriesView.setText(caloriesQuantity);

        String dailyQuantity = String.format("%.1f", r.getTotalDaily().getENERC_KCAL().getQuantity());
        daylyView.setText(dailyQuantity + "%");

        servingsEditText.setText(String.valueOf(r.getYield()));

        if (r.getTotalNutrients().getFAT() != null) {
            String fatQuantity = String.format("%.1f", r.getTotalNutrients().getFAT().getQuantity());
            fatView.setText("FAT - " + fatQuantity + " g");
        } else
            fatView.setText("FAT - " + "0" + " g");

        if (r.getTotalNutrients().getPROCNT() != null) {
            String prcntQuantity = String.format("%.1f", r.getTotalNutrients().getPROCNT().getQuantity());
            proteinView.setText("Protein - " + prcntQuantity + " g");
        } else
            proteinView.setText("Protein - " + "0" + " g");

        if (r.getTotalNutrients().getCHOCDF() != null) {
            String chocdfQuantity = String.format("%.1f", r.getTotalNutrients().getCHOCDF().getQuantity());
            carbsView.setText("Carbs - " + chocdfQuantity + " g");
        } else
            carbsView.setText("Carbs - " + "0" + " g");


        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        final CardAdapter cardAdapter = new CardAdapter(r.getIngredientLines(), this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(cardAdapter);
    }
}
