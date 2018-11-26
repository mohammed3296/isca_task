package com.example.mohammedabdullah.andoraapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    String id;
    String product_name;
    String price;
    String category;
    String description;
    String image;

    public Product(String id, String product_name, String price, String category, String description, String image) {
        this.id = id;
        this.product_name = product_name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    protected Product(Parcel in) {
        id = in.readString();
        product_name = in.readString();
        price = in.readString();
        category = in.readString();
        description = in.readString();
        image = in.readString();
    }


    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(image);
        dest.writeString(description);
        dest.writeString(product_name);
        dest.writeString(price);
        dest.writeString(category);
    }
}
