package com.aplikasi.aternuscommerce.API.JsonApi;

import com.aplikasi.aternuscommerce.API.Response.ChairResponse;
import com.aplikasi.aternuscommerce.Domain.ProductDomain;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ChairsApi {
    @GET("data_chair")
    Call<ChairResponse<ProductDomain>> getChairs();
}
