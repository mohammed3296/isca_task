package com.example.mohammedabdullah.andoraapp.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryResponse {
    @SerializedName("status")
    String status;
    @SerializedName("msg")
    String msg;
    @SerializedName("Result")
    ArrayList<Category>categories ;

    public CategoryResponse(String status, String msg, ArrayList<Category> categories) {
        this.status = status;
        this.msg = msg;
        this.categories = categories;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", categories=" + categories +
                '}';
    }
}
