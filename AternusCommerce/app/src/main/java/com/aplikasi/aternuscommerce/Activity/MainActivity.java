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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplikasi.aternuscommerce.API.Connection;
import com.aplikasi.aternuscommerce.Activity.Feature.Login;
import com.aplikasi.aternuscommerce.Activity.Feature.Profile;
import com.aplikasi.aternuscommerce.Adapter.PopularAdapter;
import com.aplikasi.aternuscommerce.Domain.PopularDomain;
import com.aplikasi.aternuscommerce.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private TextView profilename,poin;
    private ImageView Profile,Chair,Table,Lamp,Shelf,ProfileBtn,Wishlist,Cart;
    private RecyclerView recyclerViewPopuler;
    private SharedPreferences User_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profilename = findViewById(R.id.profilename);
        poin = findViewById(R.id.poin);
        Profile = findViewById(R.id.Profile);
        ProfileBtn = findViewById(R.id.Profilebtn);
        Chair = findViewById(R.id.ChairBtn);
        Table = findViewById(R.id.TableBtn);
        Lamp = findViewById(R.id.LampBtn);
        Shelf = findViewById(R.id.ShelfBtn);
        Wishlist = findViewById(R.id.WishlistBtn);// ON PROGRESS
        Cart = findViewById(R.id.CartBtn);// ON PROGRESS

        User_ID = getSharedPreferences("userSession", MODE_PRIVATE);
        boolean login_status = User_ID.contains("user_id");
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if (login_status == FALSE) {
            profilename.setVisibility(View.GONE);
            poin.setVisibility(View.GONE);
        } else {
            profilename.setVisibility(View.VISIBLE);
            poin.setVisibility(View.VISIBLE);
            ProfileName();
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

        ProfileBtn.setOnClickListener(new View.OnClickListener() {
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
            }
        });
        Table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.aplikasi.aternuscommerce.Activity.Product.TableProduct.class);
                startActivity(intent);
            }
        });
        Lamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.aplikasi.aternuscommerce.Activity.Product.LampProduct.class);
                startActivity(intent);
            }
        });
        Shelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.aplikasi.aternuscommerce.Activity.Product.ShelfProduct.class);
                startActivity(intent);
            }
        });
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.aplikasi.aternuscommerce.Activity.ShopingCart.class);
                startActivity(intent);
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

    private void ProfileName(){
        Connection connection = new Connection();
        String url = connection.getUrlSetup() + "populate.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        String userId = User_ID.getString("user_id", "");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        String fullName = jsonResponse.getString("fullname");
                        profilename.setText(fullName);
                    } else {
                        String message = jsonResponse.getString("message");
                        Toast.makeText(com.aplikasi.aternuscommerce.Activity.MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Sending Parameter Or Data into PHP API
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId); // Pass the user_id to the PHP script
                return params;
            }
        };
        queue.add(stringRequest);
    }

}
