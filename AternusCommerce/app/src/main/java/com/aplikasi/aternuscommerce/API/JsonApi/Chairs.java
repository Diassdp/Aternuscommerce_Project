package com.aplikasi.aternuscommerce.API.JsonApi;

import android.content.Context;

import com.aplikasi.aternuscommerce.API.RetrofitBuilder;

public class Chairs {
    ChairsApi chairsApi;
    public Chairs(Context context) {
        chairsApi = RetrofitBuilder
                .builder(context)
                .create(ChairsApi.class);
    }
    public ChairsApi getInstance() {
        return chairsApi;
    }
}
