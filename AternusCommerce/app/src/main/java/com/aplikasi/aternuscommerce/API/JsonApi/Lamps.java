package com.aplikasi.aternuscommerce.API.JsonApi;

import android.content.Context;

import com.aplikasi.aternuscommerce.API.RetrofitBuilder;

public class Lamps {
    LampApi lampsApi;
    public Lamps(Context context) {
        lampsApi = RetrofitBuilder
                .builder(context)
                .create(LampApi.class);
    }
    public LampApi getInstance() {
        return lampsApi;
    }
}
