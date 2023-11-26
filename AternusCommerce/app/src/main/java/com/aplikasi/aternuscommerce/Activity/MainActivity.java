package com.aplikasi.aternuscommerce.Activity;

import static java.lang.Boolean.FALSE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aplikasi.aternuscommerce.Activity.Feature.Login;
import com.aplikasi.aternuscommerce.Adapter.PopularAdapter;
import com.aplikasi.aternuscommerce.Domain.PopularDomain;
import com.aplikasi.aternuscommerce.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private ImageView Profile,Chair,Table,Lamp,Shelf;
    private RecyclerView recyclerViewPopuler;
    private SharedPreferences User_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Profile = findViewById(R.id.Profile);
        Chair = findViewById(R.id.ChairBtn);
        Table = findViewById(R.id.TableBtn);
        Lamp = findViewById(R.id.LampBtn);
        Shelf = findViewById(R.id.ShelfBtn);

        User_ID = getSharedPreferences("userSession", MODE_PRIVATE);
        boolean login_status = User_ID.contains("user_id");
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        initRecyclerView();
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.animate().scaleX(1.5f).scaleY(1.5f).setDuration(200).withEndAction(new Runnable() {
                    @Override
                    public void run() {v.setScaleX(1.0f);v.setScaleY(1.0f);}
                }).start();

                if(login_status == FALSE){
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, com.aplikasi.aternuscommerce.Activity.Feature.Profile.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        Chair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.aplikasi.aternuscommerce.Activity.Product.ChairsProduct.class);
                startActivity(intent);
                finish();
            }
        });
        Table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Lamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Shelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("Neon Hexagon Lamp", "Description 1", "item_1", 79, "Neon Lamp", 12, 4, 500));
        items.add(new PopularDomain("LED Christmas Tree Night Lamp", "Description 2", "item_2", 89, "Art Lamp", 20, 5, 350));
        items.add(new PopularDomain("LED Neon", "Description 3", "item_3", 50, "Neon Lamp", 15, 4.5, 300));
        items.add(new PopularDomain("Stand Lamp RGB", "Description 4", "item_4", 23, "Neon Lamp", 35, 4, 500));
        items.add(new PopularDomain("Feican SMD 5050", "Description 5", "item_5", 45, "Neon Lamp", 40, 4.2, 450));
        items.add(new PopularDomain("Atmosphere Lights Beats", "Description 6", "item_6", 40, "Neon Lamp", 55, 4.7, 400));

        recyclerViewPopuler = findViewById(R.id.view1);
        recyclerViewPopuler.setLayoutManager(new GridLayoutManager(this, 2));

        adapterPopular = new PopularAdapter(items);
        recyclerViewPopuler.setAdapter(adapterPopular);
    }
}
