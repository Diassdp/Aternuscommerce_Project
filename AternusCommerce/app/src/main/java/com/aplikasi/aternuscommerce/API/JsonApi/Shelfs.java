package com.aplikasi.aternuscommerce.API.JsonApi;

import android.content.Context;

import com.aplikasi.aternuscommerce.API.RetrofitBuilder;

public class Shelfs {
    ShelfApi shelfApi;
    public Shelfs(Context context) {
        shelfApi = RetrofitBuilder
                .builder(context)
                .create(ShelfApi.class);
    }
    public ShelfApi getInstance() {
        return shelfApi;
    }
}
