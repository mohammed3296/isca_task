package com.example.mohammedabdullah.andoraapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohammedabdullah.andoraapp.R;
import com.example.mohammedabdullah.andoraapp.interfaces.ListProductClickListener;
import com.example.mohammedabdullah.andoraapp.model.Product;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    public List<Product> data = Collections.emptyList();
    Product current;
    int currentPos = 0;
    final private ListProductClickListener lOnClickListener;

    public ProductAdapter(ListProductClickListener listener) {
        lOnClickListener = listener;
    }

    public void setProductData(List<Product> recipesIn, Context context) {
        data = recipesIn;
        mContext = context;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.product_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        ProductAdapter.MyHolder viewHolder = new ProductAdapter.MyHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ProductAdapter.MyHolder myHolder = (ProductAdapter.MyHolder) holder;
        current = data.get(position);
        String imageUrl = current.getImage();
        if (imageUrl != "") {
            Uri builtUri = Uri.parse(imageUrl).buildUpon().build();
            Picasso.get().load(builtUri).into(((MyHolder) holder).image);
        }

        myHolder.offer.setText("10%");
        myHolder.name.setText(current.getProduct_name());
        myHolder.price.setText(current.getPrice());
      //  myHolder.details.setText(current.getDescription());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView offer;
        TextView name;
        TextView price;
        TextView details;
        ImageView image ;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            offer = (TextView) itemView.findViewById(R.id.offer);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            details = (TextView) itemView.findViewById(R.id.details);
            image = (ImageView) itemView.findViewById(R.id.pro_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            lOnClickListener.onListItemClick(data.get(clickedPosition));
        }
    }
}
