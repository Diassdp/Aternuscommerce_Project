package com.aplikasi.aternuscommerce.API.JsonApi;

import android.content.Context;

import com.aplikasi.aternuscommerce.API.RetrofitBuilder;

public class Tables {
    TableApi tableApi;
    public Tables(Context context) {
        tableApi = RetrofitBuilder
                .builder(context)
                .create(TableApi.class);
    }
    public TableApi getInstance() {
        return tableApi;
    }
}
