package com.example.mohammedabdullah.andoraapp.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.mohammedabdullah.andoraapp.R;
import com.example.mohammedabdullah.andoraapp.adapter.CategoryAdapter;
import com.example.mohammedabdullah.andoraapp.adapter.ProductAdapter;
import com.example.mohammedabdullah.andoraapp.interfaces.ListProductClickListener;
import com.example.mohammedabdullah.andoraapp.model.Category;
import com.example.mohammedabdullah.andoraapp.model.Product;
import com.example.mohammedabdullah.andoraapp.presenter.GetArticleIntractorImpl;
import com.example.mohammedabdullah.andoraapp.presenter.MainContract;
import com.example.mohammedabdullah.andoraapp.presenter.ProductPresenterImp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements MainContract.MainView,
        ListProductClickListener {

    int editstate;
    static ArrayList<Product> products = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private MainContract.presenter presenter;
    private String key;
    ImageView imageView;
    EditText editText;
    private static ArrayList<Product> news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent = getIntent();
        key = intent.getExtras().getString("CAT");

        initializeToolbarAndRecyclerView();
        initProgressBar();
        presenter = new ProductPresenterImp(this, new GetArticleIntractorImpl(this));
        presenter.requestProductFromServer(key);
        if (savedInstanceState != null) {
            editstate = savedInstanceState.getInt("state");
            editText.setVisibility(editstate);
            news = savedInstanceState.getParcelableArrayList("data");
            ProductAdapter adapter = new ProductAdapter(ProductActivity.this);
            adapter.setProductData(news, ProductActivity.this);
            mRecyclerView.setAdapter(adapter);
        }
    }

    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    private void initializeToolbarAndRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.main_categories);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        editText = findViewById(R.id.edit);
        imageView = findViewById(R.id.search);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText.setVisibility(View.VISIBLE);
                editstate = View.VISIBLE;

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // presenter.updateFullName(s.toString());
                if (!editText.getText().toString().contains(" ")) {
                    ArrayList<Product> kjkjk = products;
                    news = newSet(kjkjk, s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                ProductAdapter adapter = new ProductAdapter(ProductActivity.this);
                adapter.setProductData(news, ProductActivity.this);
                mRecyclerView.setAdapter(adapter);
            }
        });
    }

    private void initProgressBar() {
        progressBar = findViewById(R.id.loading_indicator);
        progressBar.setIndeterminate(true);
        // progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onListItemClick(Product clickedItemIndex) {
        // Toast.makeText(this, clickedItemIndex.() + " >> " + clickedItemIndex.getName(), Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("IMAGE", clickedItemIndex.getImage());
        bundle.putString("NAME", clickedItemIndex.getProduct_name());
        bundle.putString("DETAILS", clickedItemIndex.getDescription());
        bundle.putString("PRICE", clickedItemIndex.getPrice());
        Intent recipeDetails = new Intent(ProductActivity.this, DetailsActivity.class);
        recipeDetails.putExtras(bundle);
        startActivity(recipeDetails);
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

    }

    @Override
    public void setProductToRecyclerView(ArrayList<Product> noticeArrayList) {
        products = noticeArrayList;
        ProductAdapter adapter = new ProductAdapter(this);
        adapter.setProductData(noticeArrayList, this);
        mRecyclerView.setAdapter(adapter);
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
                .make(mRecyclerView, "Error message: " + no_articles_available, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public ArrayList<Product> newSet(ArrayList<Product> products2, String word) {
        ArrayList<Product> pros = new ArrayList<>();
        try {
            int len = 0;
            len = word.length();
            for (int i = 0; i < products2.size(); i++) {
                if (products2.get(i).getProduct_name().toString()
                        .substring(0, len).equals(word)) {
                    pros.add(products2.get(i));
                }
            }
        } catch (StringIndexOutOfBoundsException f) {

        }

        return pros;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("state", editstate);
        outState.putParcelableArrayList("data", news);
    }
}
