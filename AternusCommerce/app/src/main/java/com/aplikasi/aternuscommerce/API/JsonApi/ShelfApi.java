package com.aplikasi.aternuscommerce.API.JsonApi;

import com.aplikasi.aternuscommerce.API.Response.LampResponse;
import com.aplikasi.aternuscommerce.API.Response.ShelfResponse;
import com.aplikasi.aternuscommerce.Domain.ProductDomain;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShelfApi {
    @GET("data_shelf")
    Call<ShelfResponse<ProductDomain>> getshelf();
}
