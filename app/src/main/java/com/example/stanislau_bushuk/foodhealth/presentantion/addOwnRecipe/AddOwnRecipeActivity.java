package com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.presenters.AddOwnRecipePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.view.AddOwnRecipeView;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.terrakok.cicerone.NavigatorHolder;

public class AddOwnRecipeActivity extends MvpAppCompatActivity implements AddOwnRecipeView, RecyclerViewAddOwnRecipesAdapter.Listener {

    @Inject
    NavigatorHolder navigatorHolder;

    @BindView(R.id.add_own_recipe_name_editText)
    EditText nameEditText;

    @BindView(R.id.add_own_recipe_instruction_editText)
    EditText instructionsEditText;

    @BindView(R.id.ingredients_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.add_own_recipe_recipe_image)
    ImageView recipeImage;

    @BindView(R.id.add_own_recipe_fail_name)
    TextView nameFail;

    @BindView(R.id.add_own_recipe_fail_ingredients)
    TextView ingredientsFail;

    @BindView(R.id.add_own_recipe_fail_instructions)
    TextView instructionsFail;

    @BindView(R.id.add_own_recipe_nested_scroll_view)
    NestedScrollView nestedScrollView;

    @InjectPresenter
    AddOwnRecipePresenter presenter;

    private ArrayList<OwnRecipe> ownRecipes = new ArrayList<>();

    private RecyclerViewAddOwnRecipesAdapter recyclerAdapter;

    private String imageUrl;

    @Inject
    NavigationUtil navigationUtil() {
        return new NavigationUtil(this, R.id.main_contener_frame_layout);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        App.getAppComponent().inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_own_recipe);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.add_own_recipes_action_bar_title));
        }

        ButterKnife.bind(this);
        ownRecipes.add(new OwnRecipe());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerViewAddOwnRecipesAdapter(this, ownRecipes);
        final DividerItemDecoration itemDecorator = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getResources().getDrawable(R.drawable.devider));
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setNestedScrollingEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @OnClick(R.id.add_own_recipe_button)
    public void addItem() {
        ownRecipes.add(new OwnRecipe());
        recyclerAdapter.notifyItemInserted(recyclerAdapter.getItemCount());
    }

    @OnClick(R.id.add_own_recipe_recipe_image)
    public void loadImage() {
        final Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, Constants.RESULT_LOAD_IMAGE);
    }

    @OnClick(R.id.add_own_recipe_send_button)
    public void sendRecipe() {
        final ArrayList<String> recipeIngredients = new ArrayList<>();

        for (int i = 0; i < recyclerAdapter.getItemCount(); i++) {
            recipeIngredients.add(((RecyclerViewAddOwnRecipesAdapter.MyViewHolder) recyclerView
                    .findViewHolderForAdapterPosition(i)).editText.getText().toString());
        }

        final String recipeName = nameEditText.getText().toString();
        final String recipeInstruction = instructionsEditText.getText().toString();
        presenter.validateData(recipeName, recipeIngredients, recipeInstruction,imageUrl);
    }

    @Override
    public void onDeleteButtonClick(final int pos) {
        ownRecipes.remove(pos);
        recyclerView.getChildAt(pos);
        recyclerAdapter.notifyItemRemoved(pos);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == Constants.RESULT_LOAD_IMAGE) {
            final Uri uri = data.getData();
            presenter.loadImageToStorage(uri);
        }
    }

    @Override
    public void showImage(final String imageUrl) {
        this.imageUrl=imageUrl;

        GlideApp.with(this)
                .load(imageUrl)
                .into(recipeImage);
    }

    @Override
    public void fixView(final int viewFail) {
        switch (viewFail) {

            case R.id.add_own_recipe_instruction_editText: {
                instructionsFail.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.add_own_recipe_name_editText: {
                nameFail.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.add_own_recipe_ingredients_editText: {
                ingredientsFail.setVisibility(View.VISIBLE);
            }

            default:
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }

    @Override
    public void onBackPressed() {
        presenter.exit();
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
