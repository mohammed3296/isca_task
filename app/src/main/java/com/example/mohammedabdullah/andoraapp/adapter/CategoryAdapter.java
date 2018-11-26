package com.example.mohammedabdullah.andoraapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohammedabdullah.andoraapp.R;
import com.example.mohammedabdullah.andoraapp.interfaces.ListCategoryClickListener;
import com.example.mohammedabdullah.andoraapp.model.Category;

import java.util.Collections;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    public List<Category> data = Collections.emptyList();
    Category current;
    int currentPos = 0;
    final private ListCategoryClickListener lOnClickListener;

    public CategoryAdapter(ListCategoryClickListener listener) {
        lOnClickListener = listener;
    }

    public void setCategoryData(List<Category> recipesIn, Context context) {
        data = recipesIn;
        mContext = context;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.category_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        MyHolder viewHolder = new MyHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        current = data.get(position);
        myHolder.categoryName.setText(current.getName());
        if (current.getName().equals("Kids")) {
            myHolder.cat_image.setImageResource(R.drawable.kids);
          //  Picasso.with(mContext).load("").placeholder(R.drawable.kids).into(((MyHolder) holder).cat_image);
        }
        else if (current.getName().equals("Men")){
            myHolder.cat_image.setImageResource(R.drawable.men);
          //  Picasso.with(mContext).load("").placeholder(R.drawable.men).into(((MyHolder) holder).cat_image);
        }
        else if (current.getName().equals("Women")) {
            myHolder.cat_image.setImageResource(R.drawable.women);
           // Picasso.with(mContext).load("").placeholder(R.drawable.women).into(((MyHolder) holder).cat_image);

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView categoryName;
        ImageView cat_image;

        public MyHolder(View itemView) {
            super(itemView);
            categoryName = (TextView) itemView.findViewById(R.id.catName);
            cat_image = (ImageView) itemView.findViewById(R.id.cat_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            lOnClickListener.onListItemClick(data.get(clickedPosition));
        }
    }
}
