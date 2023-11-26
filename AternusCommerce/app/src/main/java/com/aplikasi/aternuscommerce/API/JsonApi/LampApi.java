package com.aplikasi.aternuscommerce.API.JsonApi;

import com.aplikasi.aternuscommerce.API.Response.ChairResponse;
import com.aplikasi.aternuscommerce.API.Response.LampResponse;
import com.aplikasi.aternuscommerce.Domain.ProductDomain;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LampApi {
    @GET("data_lamp")
    Call<LampResponse<ProductDomain>> getLamps();
}
