package com.aplikasi.aternuscommerce.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.aplikasi.aternuscommerce.Domain.PopularDomain;
import com.aplikasi.aternuscommerce.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {
    public PopularAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
    }
    ArrayList<PopularDomain> items;
    Context context;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pup_list, parent, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
    holder.titleTxt.setText(items.get(position).getTitle());
    holder.feeTxt.setText("$"+items.get(position).getPrice());
    holder.scoreTxt.setText(""+items.get(position).getScore());
    holder.categoriTxt.setText(items.get(position).getType());

    int drawableResourceId=holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(), "drawable",holder.itemView.getContext().getPackageName());
    Glide.with(holder.itemView.getContext())
            .load(drawableResourceId)
            .transform(new GranularRoundedCorners(30,30,0,0))
            .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt, feeTxt, scoreTxt, categoriTxt;
        ImageView pic;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            feeTxt = itemView.findViewById(R.id.feeTxt);
            scoreTxt = itemView.findViewById(R.id.scoreTxt);
            categoriTxt = itemView.findViewById(R.id.categoriTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
