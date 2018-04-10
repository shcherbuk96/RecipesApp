package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.adapter.RecyclerAdapter;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters.SearchPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.view.ViewSearch;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CardActivity extends MvpAppCompatActivity implements CardView{

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

    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card);

        ButterKnife.bind(this);

        Intent i=getIntent();

        String uri=i.getStringExtra("recipe");
        Timber.e(uri);
        presenter.getRecipeFromUri(uri);


       // final List<Hits> hitsList = new ArrayList<>();
/*        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerAdapter = new RecyclerAdapter(hitsList, this);
        listRecyclerView.setLayoutManager(mLayoutManager);
        listRecyclerView.setAdapter(recyclerAdapter);
        presenter.searchObservable(searchView);*/

    }


    @Override
    public void showList(final Recipe recipe) {
       /* Recipe recipe=hitsList.get(0).getRecipe();
        GlideApp
                .with(this)
                .load(recipe.getImage())
                .centerCrop()
                .into(photoView);

        caloriesView.setText(String.valueOf(recipe.getCalories()));*/
    }

    @Override
    public void progressBarVisible(final int visible) {
        //searchProgressBar.setVisibility(visible);
    }
}
