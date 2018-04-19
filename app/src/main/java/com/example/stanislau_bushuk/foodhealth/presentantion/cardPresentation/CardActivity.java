package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ButterKnife.bind(this);

        final RealmList<String> recipe = new RealmList<>();

        initAdapter(recipe);

        presenter.getRecipeFromUri(getIntent().getStringExtra(Constants.RECIPE_INTENT_KEY));
        //http://www.edamam.com/ontologies/edamam.owl#recipe_aac66f3688a63daa664b2ac0adff1c11
    }

    @Override
    public void showList(final Data data) {
        Timber.e(String.valueOf(data));
        setTitle(data.getLabel());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(30));
        GlideApp
                .with(this)
                .load(data.getImage())
                .apply(requestOptions)
                .into(photoView);

        caloriesView.setText(getString(R.string.card_number_calories, data.getCalories()));
        daylyView.setText(getString(R.string.card_number_daily, data.getENERC_KCAL().getQuantity()));
        servingsEditText.setText(String.valueOf(data.getYield()));
        fatView.setText(getString(R.string.card_number_fat, data.getFat().getQuantity()));
        proteinView.setText(getString(R.string.card_number_protein, data.getProt().getQuantity()));
        carbsView.setText(getString(R.string.card_number_carbs, data.getChocdf().getQuantity()));
        initAdapter(data.getIngredientLines());
    }

    private void initAdapter(final RealmList<String> recipe) {

        if (cardAdapter == null) {
            cardAdapter = new CardAdapter(recipe);
        } else {
            cardAdapter.updateData(recipe);
        }

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(cardAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
