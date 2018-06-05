package com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewAddOwnRecipesAdapter extends RecyclerView.Adapter<RecyclerViewAddOwnRecipesAdapter.MyViewHolder> {

    private List<OwnRecipe> hits;
    private Listener listener;

    public RecyclerViewAddOwnRecipesAdapter(final Listener listener, final List<OwnRecipe> hits) {
        this.hits = hits;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_add_own_recipes, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return hits.size();
    }

    public interface Listener {
        void onDeleteButtonClick(int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.add_own_recipe_ingredients_editText)
        EditText editText;

        @BindView(R.id.add_own_recipe_remove_button)
        ImageButton deleteButton;

        @BindView(R.id.add_own_recipe_linear_layout)
        LinearLayout linearLayout;

        MyViewHolder(final View view) {
            super(view);

            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.add_own_recipe_remove_button)
        public void click() {
            editText.setText(null);
            listener.onDeleteButtonClick(getAdapterPosition());
        }


    }
}
