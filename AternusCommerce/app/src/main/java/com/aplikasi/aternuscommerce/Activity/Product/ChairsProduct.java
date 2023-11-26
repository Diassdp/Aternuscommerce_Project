package com.aplikasi.aternuscommerce.Activity.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.aplikasi.aternuscommerce.API.JsonApi.Chairs;
import com.aplikasi.aternuscommerce.API.Response.ChairResponse;
import com.aplikasi.aternuscommerce.Adapter.Product.ChairsAdapter;
import com.aplikasi.aternuscommerce.Domain.ProductDomain;
import com.aplikasi.aternuscommerce.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChairsProduct extends AppCompatActivity {
    Chairs chairs;
    ChairsAdapter adapter;
    RecyclerView rv_games;
    TextView debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chairs_product);
        rv_games = findViewById(R.id.rv_games);
        debug = findViewById(R.id.debug);
        chairs = new Chairs(this);
        rv_games.setHasFixedSize(true);
        rv_games.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChairsAdapter(chairs);
        rv_games.setAdapter(adapter);
        getDataPlayer();
    }
    public void getDataPlayer(){
        chairs.getInstance().getChairs().enqueue(new Callback<ChairResponse<ProductDomain>>() {
            @Override
            public void onResponse(Call<ChairResponse<ProductDomain>> call, Response<ChairResponse<ProductDomain>> response) {
                if (response.isSuccessful()) {
                    ChairResponse<ProductDomain> resp = response.body();
                    if (resp != null) {
                        List<ProductDomain> resultList = resp.getResult();
                        if (resultList != null && !resultList.isEmpty()) {
                            adapter = new ChairsAdapter(resultList, ChairsProduct.this);
                            rv_games.setAdapter(adapter);
                        } else {
                            Log.e("Debug", "Response result is empty");
                        }
                    } else {
                        Log.e("Debug", "Response body is null");
                    }
                } else {
                    Log.e("Debug", "Unsuccessful response: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<ChairResponse<ProductDomain>> call, Throwable t) {
                Toast.makeText(ChairsProduct.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                debug.setText( t.getMessage());
            }
        });
    }

}