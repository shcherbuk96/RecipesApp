package com.example.stanislau_bushuk.foodhealth.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.Modul.GlideApp;
import com.example.stanislau_bushuk.foodhealth.R;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<Recipe> items;


    public RecyclerAdapter(List<Recipe> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Recipe recipe = items.get(position);
        holder.title.setText(recipe.getLabel());
        GlideApp
                .with(context)
                .load(recipe.getImage())
                .centerCrop()
                .into(holder.image);

/*        Picasso
                .with(context)
                .load(photo.getUrl())
                .error(R.drawable.error)
                .fit()
                .centerCrop()
                .into(holder.photo);*/
/*        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ItemActivity.class);
                intent.putExtra(Constants.KEY_NAME, photo.getTitle());
                intent.putExtra(Constants.KEY_URL, photo.getUrl());
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return items.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView title;
        @BindView(R.id.item_image)
        ImageView image;
        //RelativeLayout relative;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

