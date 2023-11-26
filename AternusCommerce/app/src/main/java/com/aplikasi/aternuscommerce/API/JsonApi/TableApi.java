package com.aplikasi.aternuscommerce.API.JsonApi;

import com.aplikasi.aternuscommerce.API.Response.LampResponse;
import com.aplikasi.aternuscommerce.API.Response.TableResponse;
import com.aplikasi.aternuscommerce.Domain.ProductDomain;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TableApi {
    @GET("data_table")
    Call<TableResponse<ProductDomain>> getTable();
}
