package com.example.mohammedabdullah.andoraapp.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mohammedabdullah.andoraapp.R;
import com.example.mohammedabdullah.andoraapp.adapter.CategoryAdapter;
import com.example.mohammedabdullah.andoraapp.interfaces.ListCategoryClickListener;
import com.example.mohammedabdullah.andoraapp.model.Category;
import com.example.mohammedabdullah.andoraapp.model.Product;
import com.example.mohammedabdullah.andoraapp.presenter.GetArticleIntractorImpl;
import com.example.mohammedabdullah.andoraapp.presenter.MainContract;
import com.example.mohammedabdullah.andoraapp.presenter.MainPresenterImpl;
import java.util.ArrayList;


public class CategoryActivity extends AppCompatActivity implements MainContract.MainView,
        ListCategoryClickListener {

    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private MainContract.presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initializeToolbarAndRecyclerView();
        initProgressBar();
        presenter = new MainPresenterImpl(this, new GetArticleIntractorImpl(this));
        presenter.requestDataFromServer();
    }


    private void initializeToolbarAndRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.main_categories);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
        }
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        if (isTablet(this)) {
            GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        mRecyclerView.setHasFixedSize(true);
    }

    private void initProgressBar() {
        progressBar = findViewById(R.id.loading_indicator);
        progressBar.setIndeterminate(true);
        // progressBar.setVisibility(View.INVISIBLE);
    }
    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }


    @Override
    public void onListItemClick(Category clickedItemIndex) {
        Bundle bundle = new Bundle();
        bundle.putString("CAT", clickedItemIndex.getName());
        Intent i = new Intent(CategoryActivity.this, ProductActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void setDataToRecyclerView(ArrayList<Category> noticeArrayList) {

        CategoryAdapter adapter = new CategoryAdapter(this);
        adapter.setCategoryData(noticeArrayList, this);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void setProductToRecyclerView(ArrayList<Product> noticeArrayList) {

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Snackbar snackbar = Snackbar
                .make(mRecyclerView, "Something went wrong...Error message: " + throwable.getMessage(), Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    @Override
    public void onInternetFailure(String msg) {
        Snackbar snackbar = Snackbar
                .make(mRecyclerView, "Something went wrong...Error message: " + msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onDataFailure(String no_articles_available) {
        Snackbar snackbar = Snackbar
                .make(mRecyclerView,"Error message: " + no_articles_available, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
