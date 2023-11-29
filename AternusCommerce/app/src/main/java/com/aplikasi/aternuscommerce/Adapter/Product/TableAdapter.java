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
import com.aplikasi.aternuscommerce.Domain.Table;
import com.aplikasi.aternuscommerce.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableHolder>{
    public Context context;
    private List<Table> tableList;
    public TableAdapter(Context context , List<Table> table){
        this.context = context;
        tableList = table;
    }
    @NonNull
    @Override
    public TableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_list, parent,false);
        return new TableHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableHolder holder, int position) {

        Table table = tableList.get(position);
        holder.tittle.setText(table.getTittle().toString());
        holder.category.setText(table.getCategory().toString());
        holder.price.setText(table.getPrice().toString());
        Glide.with(context).load(table.getPoster()).into(holder.poster);

        holder.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Added to wishlist: " + table.getTittle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailChair.class);

                Bundle bundle =  new Bundle();
                bundle.putString("title", table.getTittle());
                bundle.putString("description", table.getDescription());
                bundle.putString("category", table.getCategory());
                bundle.putString("price", table.getPrice());
                bundle.putString("score", table.getScore());
                bundle.putString("review", table.getReview());
                bundle.putString("poster", table.getPoster());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public class TableHolder extends RecyclerView.ViewHolder{
        ImageView poster;
        TextView tittle, category, price;
        Button wishlist, detail;
        ConstraintLayout constraintLayout;
        public TableHolder(@NonNull View itemView) {
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
