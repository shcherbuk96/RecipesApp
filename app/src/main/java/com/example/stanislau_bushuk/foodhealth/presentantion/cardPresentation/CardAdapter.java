package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stanislau_bushuk.foodhealth.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private RealmList<String> list;

    CardAdapter(final RealmList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.titleTextView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(final RealmList<String> ingridients) {
        list.clear();
        list.addAll(ingridients);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_card_title_text_view)
        TextView titleTextView;

        MyViewHolder(final View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}

