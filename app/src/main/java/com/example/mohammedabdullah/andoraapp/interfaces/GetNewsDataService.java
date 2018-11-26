package com.example.mohammedabdullah.andoraapp.interfaces;

import com.example.mohammedabdullah.andoraapp.model.CategoryResponse;
import com.example.mohammedabdullah.andoraapp.model.ProductResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetNewsDataService {

    @GET("task/cat_list.php")
    Call<CategoryResponse> getCategoryData();

    @FormUrlEncoded
    @POST("task/pro_list.php")
    Call<ProductResponse> getProducts(@Field("category") String category);

}