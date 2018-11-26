package com.example.mohammedabdullah.andoraapp.presenter;


import com.example.mohammedabdullah.andoraapp.model.Category;
import com.example.mohammedabdullah.andoraapp.model.Product;

import java.util.ArrayList;


public interface MainContract {


    interface presenter{

        void onDestroy();
        void requestDataFromServer();
        void requestProductFromServer(String key);

    }


    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<Category> noticeArrayList);
        void setProductToRecyclerView(ArrayList<Product> noticeArrayList);

        void onResponseFailure(Throwable throwable);
        void onInternetFailure(String msg);

        void onDataFailure(String no_articles_available);
    }

    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Category> noticeArrayList);
            void onFailure(Throwable t);
            void onNoInternet(String m);
            void onNoArticles(String no_articles_available);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener);
    }



    interface GetProductIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Product> noticeArrayList);
            void onFailure(Throwable t);
            void onNoInternet(String m);
            void onNoProducts(String no_products);
        }

        void getProductArrayList(OnFinishedListener onFinishedListener , String key);
    }
}
