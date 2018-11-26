package com.example.mohammedabdullah.andoraapp.presenter;

import com.example.mohammedabdullah.andoraapp.model.Product;

import java.util.ArrayList;

public class ProductPresenterImp implements MainContract.presenter, MainContract.GetProductIntractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetProductIntractor getNoticeIntractor;
    public ProductPresenterImp(MainContract.MainView mainView, MainContract.GetProductIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }
    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void requestDataFromServer() {
    }

    @Override
    public void requestProductFromServer(String key) {
        getNoticeIntractor.getProductArrayList(this  , key);
    }

    @Override
    public void onFinished(ArrayList<Product> noticeArrayList) {
        if(mainView != null){
            mainView.setProductToRecyclerView(noticeArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }

    @Override
    public void onNoInternet(String m) {
        if(mainView != null){
            mainView.onInternetFailure(m);
            mainView.hideProgress();
        }
    }

    @Override
    public void onNoProducts(String no_products) {
        if(mainView != null){
            mainView.onDataFailure(no_products);
            mainView.hideProgress();
        }
    }
}
