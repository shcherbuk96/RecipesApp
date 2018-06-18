package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.NavigationUtil;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Comment;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;
import com.google.firebase.auth.FirebaseAuth;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import ru.terrakok.cicerone.NavigatorHolder;

public class CardActivity extends MvpAppCompatActivity implements CardView {

    FirebaseAuth firebaseAuth;

    @BindView(R.id.card_comments_recycler_view)
    RecyclerView commentsRecyclerView;

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

    @BindView(R.id.card_comments_number)
    TextView countCommentsView;

    @BindView(R.id.card_user_comment_edit_text)
    EditText commentUserView;

    @BindView(R.id.card_comment_send_image_view)
    ImageView sendView;

    @InjectPresenter
    CardPresenter presenter;

    @Inject
    NavigatorHolder navigatorHolder;

    private CardAdapter cardAdapter;
    private CardCommentsAdapter cardCommentsAdapter;
    private Data data;

    @Inject
    NavigationUtil navigationUtil() {
        return new NavigationUtil(this, R.id.main_contener_frame_layout);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        App.getAppComponent().inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new CardAdapter(new ArrayList<String>());
        final DividerItemDecoration itemDecorator = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getResources().getDrawable(R.drawable.devider));
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(cardAdapter);

        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardCommentsAdapter = new CardCommentsAdapter(new ArrayList<Comment>());
        commentsRecyclerView.addItemDecoration(itemDecorator);
        commentsRecyclerView.setAdapter(cardCommentsAdapter);

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

    @OnClick(R.id.card_comment_send_image_view)
    public void sendComment() {
        if (firebaseAuth.getCurrentUser() != null) {
            presenter.addComment(firebaseAuth.getCurrentUser().getEmail(),
                    commentUserView.getText().toString(),
                    data.getLabel(),
                    firebaseAuth.getUid());
            commentUserView.setText(null);
        }
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
    public void showComments(final List<Comment> listComments) {
        countCommentsView.setText(getString(R.string.card_comments, listComments.size()));
        cardCommentsAdapter.updateData(listComments);
    }

    @Override
    public void showAnonymous() {
        Toast.makeText(this, getString(R.string.card_comments_anonymous), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.card_error), Toast.LENGTH_SHORT).show();
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
