package com.aplikasi.aternuscommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.aplikasi.aternuscommerce.Activity.Feature.Login;
import com.aplikasi.aternuscommerce.Activity.Feature.Profile;
import com.aplikasi.aternuscommerce.R;

public class ShopingCart extends AppCompatActivity {
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_cart);
        back = findViewById(R.id.BackBtn);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Shoping Cart");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopingCart.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
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