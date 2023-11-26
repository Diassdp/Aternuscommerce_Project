package com.aplikasi.aternuscommerce.Adapter.Product;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aplikasi.aternuscommerce.API.JsonApi.Shelfs;
import com.aplikasi.aternuscommerce.Domain.ProductDomain;
import com.aplikasi.aternuscommerce.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ShelfAdapter extends RecyclerView.Adapter<ShelfAdapter.ViewHolder>{
    List<ProductDomain> result;
    Activity activity;
    public ShelfAdapter(List<ProductDomain> result, Activity activity){
        this.result = result;
        this.activity = activity;
    }
    public ShelfAdapter(Shelfs shelfs) {
    }

    @NonNull
    @Override
    public ShelfAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShelfAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_shelf_list, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ShelfAdapter.ViewHolder holder, int position) {
        holder.bind(result.get(position));
    }
    @Override
    public int getItemCount() {
        return result != null ? result.size() : 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, feeTxt, scoreTxt, categoriTxt;
        ImageView poster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            feeTxt = itemView.findViewById(R.id.feeTxt);
            scoreTxt = itemView.findViewById(R.id.scoreTxt);
            categoriTxt = itemView.findViewById(R.id.categoriTxt);
            poster = itemView.findViewById(R.id.pic);
        }
        public void bind(ProductDomain productDomain) {
            titleTxt.setText(productDomain.getTitle());
            feeTxt.setText(productDomain.getPrice());
            scoreTxt.setText(productDomain.getScore());
            categoriTxt.setText(productDomain.getCategory());
            Glide.with(activity).load(productDomain.getPoster()).into(poster);
        }
    }
}
