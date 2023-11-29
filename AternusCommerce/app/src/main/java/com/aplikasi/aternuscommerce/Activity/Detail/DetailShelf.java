package com.aplikasi.aternuscommerce.Activity.Detail;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aplikasi.aternuscommerce.R;
import com.bumptech.glide.Glide;

public class DetailShelf extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView =  findViewById(R.id.posterView);
        TextView title_tv = findViewById(R.id.tv_name);
        TextView category_tv = findViewById(R.id.tv_category);
        TextView score_tv = findViewById(R.id.tv_score);
        TextView review_tv = findViewById(R.id.tv_review);
        TextView description_tv = findViewById(R.id.tv_description);
        TextView price_tv = findViewById(R.id.tv_price);

        Bundle bundle = getIntent().getExtras();

        String title = bundle.getString("title");
        String category = bundle.getString("category");
        String score = bundle.getString("score");
        String review = bundle.getString("review");
        String description = bundle.getString("description");
        String price = bundle.getString("price");
        String poster = bundle.getString("poster");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Glide.with(this).load(poster).into(imageView);
        title_tv.setText(title.toString());
        category_tv.setText(category.toString());
        score_tv.setText(score.toString());
        price_tv.setText(price.toString());
        review_tv.setText(review.toString());
        description_tv.setText(description.toString());
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}