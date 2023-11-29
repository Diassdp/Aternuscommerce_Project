package com.aplikasi.aternuscommerce.Activity.Product;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.aplikasi.aternuscommerce.Adapter.Product.LampAdapter;
import com.aplikasi.aternuscommerce.Domain.Lamp;
import com.aplikasi.aternuscommerce.R;
import com.aplikasi.aternuscommerce.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LampProduct extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Lamp> lampList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_product);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        lampList = new ArrayList<>();
        fetchdata();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("List Lamp");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void fetchdata() {
        String url="https://raw.githubusercontent.com/ahmadnazhmy/aternuscommerce_data/main/data_lamp";
        JsonArrayRequest jsonArrayRequest =  new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0 ; i < response.length() ; i ++ ){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String description = jsonObject.getString("description");
                        String category = jsonObject.getString("category");
                        String price = jsonObject.getString("price");
                        String review = jsonObject.getString("review");
                        String score = jsonObject.getString("score");
                        String poster = jsonObject.getString("poster");

                        Lamp lamp = new Lamp(title, description, category, price,review,score,poster);
                        lampList.add(lamp);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    LampAdapter adapter = new LampAdapter(LampProduct.this , lampList);

                    recyclerView.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LampProduct.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// Kembali ke halaman sebelumnya
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}