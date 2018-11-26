package com.example.mohammedabdullah.andoraapp.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.mohammedabdullah.andoraapp.interfaces.GetNewsDataService;
import com.example.mohammedabdullah.andoraapp.model.Category;
import com.example.mohammedabdullah.andoraapp.model.CategoryResponse;
import com.example.mohammedabdullah.andoraapp.model.Product;
import com.example.mohammedabdullah.andoraapp.model.ProductResponse;
import com.example.mohammedabdullah.andoraapp.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetArticleIntractorImpl implements MainContract.GetNoticeIntractor  , MainContract.GetProductIntractor {

    Context context;

    public GetArticleIntractorImpl(Context context1) {
        this.context = context1;
    }

    @Override
    public void getNoticeArrayList(final MainContract.GetNoticeIntractor.OnFinishedListener onFinishedListener) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            GetNewsDataService service = RetrofitInstance.getRetrofitInstance().create(GetNewsDataService.class);

            Call<CategoryResponse> call = service.getCategoryData();
            Log.wtf("URL Called", call.request().url() + "");

            call.enqueue(new Callback<CategoryResponse>() {
                @Override
                public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                    if (response.body() != null)
                        onFinishedListener.onFinished((ArrayList<Category>) response.body().getCategories());
                    else
                        onFinishedListener.onNoArticles("No Categories available");
                }

                @Override
                public void onFailure(Call<CategoryResponse> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        } else {
            onFinishedListener.onNoInternet("No Internet Connection ");
        }

    }

    @Override
    public void getProductArrayList(final MainContract.GetProductIntractor.OnFinishedListener onFinishedListener , String key) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            GetNewsDataService service = RetrofitInstance.getRetrofitInstance().create(GetNewsDataService.class);

            Call<ProductResponse> call = service.getProducts(key);
            Log.wtf("URL Called", call.request().url() + "");

            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if (response.body() != null)
                        onFinishedListener.onFinished((ArrayList<Product>) response.body().getResult());
                    else
                        onFinishedListener.onNoProducts("No Products available");
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        } else {
            onFinishedListener.onNoInternet("No Internet Connection ");
        }
    }
}
