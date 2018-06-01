package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.NavigationUtil;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import ru.terrakok.cicerone.NavigatorHolder;

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

    @Inject
    NavigatorHolder navigatorHolder;

    private CardAdapter cardAdapter;

    private Data data;

    @Inject
    NavigationUtil navigationUtil() {
        return new NavigationUtil(this);
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new CardAdapter(new ArrayList<String>());
        final DividerItemDecoration itemDecorator = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getResources().getDrawable(R.drawable.devider));
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(cardAdapter);

        if (savedInstanceState == null) {
            presenter.getRecipeFromRealmUri(getIntent().getStringExtra(Constants.RECIPE_INTENT_KEY));
        }

        RxTextView
                .textChanges(servingsEditText)
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(final CharSequence charSequence) throws Exception {
                        return charSequence.toString();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(final Disposable d) {

                    }

                    @Override
                    public void onNext(final String s) {
                        if (!s.isEmpty()) {
                            presenter.getEditData(Float.parseFloat(s), data);
                        }
                    }

                    @Override
                    public void onError(final Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //http://www.edamam.com/ontologies/edamam.owl#recipe_aac66f3688a63daa664b2ac0adff1c11
    }

    @Override
    public void showList(final Data data) {
        this.data = data;
        setTitle(data.getLabel());

        GlideApp
                .with(this)
                .load(data.getImage())
                .centerCrop()
                .into(photoView);

        caloriesView.setText(getString(R.string.card_number_calories, data.getCalories()));
        daylyView.setText(getString(R.string.card_number_daily, data.getENERC_KCAL().getQuantity()));
        servingsEditText.setText(String.valueOf(data.getYield()));
        fatView.setText(getString(R.string.card_number_fat, data.getFat().getQuantity()));
        proteinView.setText(getString(R.string.card_number_protein, data.getProt().getQuantity()));
        carbsView.setText(getString(R.string.card_number_carbs, data.getChocdf().getQuantity()));
        cardAdapter.updateData(data.getIngredientLines());
    }

    @Override
    public void showEditData(final EditData data) {
        caloriesView.setText(getString(R.string.card_number_calories, data.getCalories()));
        daylyView.setText(getString(R.string.card_number_daily, data.getENERC_KCAL()));
        fatView.setText(getString(R.string.card_number_fat, data.getFat()));
        proteinView.setText(getString(R.string.card_number_protein, data.getProt()));
        carbsView.setText(getString(R.string.card_number_carbs, data.getChocdf()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        presenter.back();
        return true;

    }

    @Override
    public void onBackPressed() {
        presenter.back();
    }

    @Override
    protected void onResume() {
        super.onResume();

        navigatorHolder.setNavigator(navigationUtil());
    }

    @Override
    protected void onPause() {
        super.onPause();

        navigatorHolder.removeNavigator();
    }
}
