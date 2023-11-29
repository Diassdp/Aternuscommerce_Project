package com.aplikasi.aternuscommerce.Adapter.Product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.aplikasi.aternuscommerce.Activity.Detail.DetailChair;
import com.aplikasi.aternuscommerce.Domain.Chair;
import com.aplikasi.aternuscommerce.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ChairsAdapter extends RecyclerView.Adapter<ChairsAdapter.ChairHolder> {
    public Context context;
    private List<Chair> chairList;
    public ChairsAdapter(Context context , List<Chair> chairs){
        this.context = context;
        chairList = chairs;
    }
    @NonNull
    @Override
    public ChairHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_list, parent,false);
        return new ChairHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChairHolder holder, int position) {

        Chair chair = chairList.get(position);
        holder.tittle.setText(chair.getTittle().toString());
        holder.category.setText(chair.getCategory().toString());
        holder.price.setText(chair.getPrice().toString());
        Glide.with(context).load(chair.getPoster()).into(holder.poster);

        holder.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Added to wishlist: " + chair.getTittle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailChair.class);

                Bundle bundle =  new Bundle();
                bundle.putString("title", chair.getTittle());
                bundle.putString("description", chair.getDescription());
                bundle.putString("category", chair.getCategory());
                bundle.putString("price", chair.getPrice());
                bundle.putString("score", chair.getScore());
                bundle.putString("review", chair.getReview());
                bundle.putString("poster", chair.getPoster());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chairList.size();
    }

    public class ChairHolder extends RecyclerView.ViewHolder{
        ImageView poster;
        TextView tittle, category, price;
        Button wishlist, detail;
        ConstraintLayout constraintLayout;

        public ChairHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.posterView);
            tittle = itemView.findViewById(R.id.tv_name);
            category = itemView.findViewById(R.id.tv_category);
            price = itemView.findViewById(R.id.tv_price);
            wishlist = itemView.findViewById(R.id.btn_wishlist);
            detail = itemView.findViewById(R.id.btn_detail);
        }
    }
}
