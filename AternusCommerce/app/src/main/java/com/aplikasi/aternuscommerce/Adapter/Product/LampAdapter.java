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
import com.aplikasi.aternuscommerce.Domain.Lamp;
import com.aplikasi.aternuscommerce.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class LampAdapter extends RecyclerView.Adapter<LampAdapter.LampHolder>{
    public Context context;
    private List<Lamp> lampList;
    public LampAdapter(Context context , List<Lamp> lamp){
        this.context = context;
        lampList = lamp;
    }
    @NonNull
    @Override
    public LampHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_list, parent,false);
        return new LampHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LampHolder holder, int position) {

        Lamp lamp = lampList.get(position);
        holder.title.setText(lamp.getTittle().toString());
        holder.category.setText(lamp.getCategory().toString());
        holder.price.setText(lamp.getPrice().toString());
        holder.review.setText(lamp.getReview().toString());
        holder.score.setText(lamp.getScore().toString());
        Glide.with(context).load(lamp.getPoster()).into(holder.poster);

        holder.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Item Added to Wishlist: " + lamp.getTittle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailChair.class);

                Bundle bundle =  new Bundle();
                bundle.putString("title", lamp.getTittle());
                bundle.putString("description", lamp.getDescription());
                bundle.putString("category", lamp.getCategory());
                bundle.putString("price", lamp.getPrice());
                bundle.putString("score", lamp.getScore());
                bundle.putString("review", lamp.getReview());
                bundle.putString("poster", lamp.getPoster());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lampList.size();
    }

    public class LampHolder extends RecyclerView.ViewHolder{
        ImageView poster,wishlist;
        TextView title, category, price,review,score;
        Button cart;
        ConstraintLayout constraintLayout,detail;

        public LampHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.pic);
            title = itemView.findViewById(R.id.titleTxt);
            category = itemView.findViewById(R.id.categoriTxt);
            price = itemView.findViewById(R.id.feeTxt);
            wishlist = itemView.findViewById(R.id.WishlistBtn);
            review = itemView.findViewById(R.id.review);
            score = itemView.findViewById(R.id.scoreTxt);
            detail = itemView.findViewById(R.id.btn_detail);
        }
    }
}
