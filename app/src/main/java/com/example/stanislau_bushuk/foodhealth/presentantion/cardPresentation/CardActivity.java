package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stanislau_bushuk.foodhealth.R;

import javax.inject.Inject;

import butterknife.BindView;

public class CardActivity extends AppCompatActivity {

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

    @Inject

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card);

        Intent i=getIntent();
        String uri=i.getStringExtra("uri");

    }

}
