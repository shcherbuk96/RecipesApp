package com.example.stanislau_bushuk.foodhealth.presentantion.cardOwnRecipePresentation;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.NavigationUtil;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.NavigatorHolder;
import timber.log.Timber;

public class CardOwnRecipeActivity extends MvpAppCompatActivity implements CardOwnRecipeView {
    FirebaseAuth firebaseAuth;

    @BindView(R.id.card_own_photo_image_view)
    ImageView photo;

    @BindView(R.id.card_own_instruction_text_view)
    TextView instruction;

    @BindView(R.id.card_own_ingredients_recycler_view)
    RecyclerView recyclerView;

    @InjectPresenter
    CardOwnRecipePresenter cardOwnRecipePresenter;

    @Inject
    NavigatorHolder navigatorHolder;

    private CardAdapter cardAdapter;

    @Inject
    NavigationUtil navigationUtil() {
        return new NavigationUtil(this, R.id.main_contener_frame_layout);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        App.getAppComponent().inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_own_recipe);

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        firebaseAuth = FirebaseAuth.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new CardAdapter(new ArrayList<String>());
        final DividerItemDecoration itemDecorator = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getResources().getDrawable(R.drawable.devider));
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(cardAdapter);

        cardOwnRecipePresenter.getInfoOwnRecipe(getIntent().getStringExtra(Constants.CARD_OWN_RECIPE_NAME), firebaseAuth.getCurrentUser().getUid());
    }

    @Override
    public void infoOwnRecipe(final OwnRecipe ownRecipe) {
        setTitle(ownRecipe.getRecipeName());
        instruction.setText(ownRecipe.getRecipeInstruction());

        GlideApp
                .with(this)
                .load(ownRecipe.getRecipePhoto())
                .centerCrop()
                .into(photo);

        cardAdapter.updateDataArrayList(ownRecipe.getRecipeIngridients());
    }

    @Override
    public void error(final String error) {
        Snackbar.make(findViewById(R.id.card_own_instruction_text_view), error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        cardOwnRecipePresenter.back();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
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
