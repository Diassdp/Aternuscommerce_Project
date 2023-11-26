package com.aplikasi.aternuscommerce.Activity.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.aplikasi.aternuscommerce.API.JsonApi.Tables;
import com.aplikasi.aternuscommerce.API.Response.TableResponse;
import com.aplikasi.aternuscommerce.Adapter.Product.TableAdapter;
import com.aplikasi.aternuscommerce.Domain.ProductDomain;
import com.aplikasi.aternuscommerce.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableProduct extends AppCompatActivity {
    Tables tables;
    TableAdapter adapter;
    RecyclerView rv_games;
    TextView debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_product);
        rv_games = findViewById(R.id.rv_games);
        debug = findViewById(R.id.debug);
        tables = new Tables(this);
        rv_games.setHasFixedSize(true);
        rv_games.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TableAdapter(tables);
        rv_games.setAdapter(adapter);
        getDataPlayer();
    }
    public void getDataPlayer(){
        tables.getInstance().getTable().enqueue(new Callback<TableResponse<ProductDomain>>() {
            @Override
            public void onResponse(Call<TableResponse<ProductDomain>> call, Response<TableResponse<ProductDomain>> response) {
                if (response.isSuccessful()) {
                    TableResponse<ProductDomain> resp = response.body();
                    if (resp != null) {
                        List<ProductDomain> resultList = resp.getResult();
                        if (resultList != null && !resultList.isEmpty()) {
                            adapter = new TableAdapter(resultList, TableProduct.this);
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
            public void onFailure(Call<TableResponse<ProductDomain>> call, Throwable t) {
                Toast.makeText(TableProduct.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                debug.setText( t.getMessage());
            }
        });
    }

}