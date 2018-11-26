package com.example.mohammedabdullah.andoraapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductResponse {
    @SerializedName("status")
    String status;
    @SerializedName("msg")
    String msg;
    @SerializedName("Result")
    ArrayList<Product> products;

    public ProductResponse(String status, String msg, ArrayList<Product> result) {
        this.status = status;
        this.msg = msg;
        products = result;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<Product> getResult() {
        return products;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(ArrayList<Product> result) {
        products = result;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", Result=" + products +
                '}';
    }
}
