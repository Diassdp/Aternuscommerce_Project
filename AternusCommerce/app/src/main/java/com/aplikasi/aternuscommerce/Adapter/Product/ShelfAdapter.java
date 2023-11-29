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
import com.aplikasi.aternuscommerce.Domain.Shelf;
import com.aplikasi.aternuscommerce.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ShelfAdapter extends RecyclerView.Adapter<ShelfAdapter.ShelfHolder>{
    public Context context;
    private List<Shelf> shelfList;
    public ShelfAdapter(Context context , List<Shelf> shelf){
        this.context = context;
        shelfList = shelf;
    }
    @NonNull
    @Override
    public ShelfHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_list, parent,false);
        return new ShelfHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShelfHolder holder, int position) {

        Shelf shelf = shelfList.get(position);
        holder.title.setText(shelf.getTittle().toString());
        holder.category.setText(shelf.getCategory().toString());
        holder.price.setText(shelf.getPrice().toString());
        holder.review.setText(shelf.getReview().toString());
        holder.score.setText(shelf.getScore().toString());
        Glide.with(context).load(shelf.getPoster()).into(holder.poster);

        holder.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Added to wishlist: " + shelf.getTittle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailChair.class);

                Bundle bundle =  new Bundle();
                bundle.putString("title", shelf.getTittle());
                bundle.putString("description", shelf.getDescription());
                bundle.putString("category", shelf.getCategory());
                bundle.putString("price", shelf.getPrice());
                bundle.putString("score", shelf.getScore());
                bundle.putString("review", shelf.getReview());
                bundle.putString("poster", shelf.getPoster());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shelfList.size();
    }

    public class ShelfHolder extends RecyclerView.ViewHolder{
        ImageView poster,wishlist;
        TextView title, category, price,review,score;
        Button cart;
        ConstraintLayout constraintLayout,detail;

        public ShelfHolder(@NonNull View itemView) {
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
