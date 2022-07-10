package com.example.ecommerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.Interfaces.ClickListener;
import com.example.ecommerceapp.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemsListAdaptor extends RecyclerView.Adapter<ItemsListAdaptor.ViewHolder> {
        protected int mLayout;
        protected Context mContext;
        protected List<Item> mItems;
        protected ClickListener clickListener;
        protected RecyclerView recyclerView;

    public ItemsListAdaptor(@NonNull Context context, int resource, @NonNull ArrayList<Item> items, ClickListener clickListener) {
        mContext = context;
        mLayout = resource;
        mItems = items;
        this.clickListener = clickListener;
    }

    public void setFragment(ItemsFragment fragment){
        this.recyclerView = fragment.getView().findViewById(R.id.items_recycler_view);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(v, clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.itemID.setText(String.valueOf(mItems.get(position).getId()));
        viewHolder.itemName.setText(mItems.get(position).getName());
        viewHolder.itemPrice.setText(String.valueOf(mItems.get(position).getSellPrice()));
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mItems.get(viewHolder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView itemID, itemName, itemPrice;
            RecyclerView recyclerView;
            ClickListener clickListener;

            public ViewHolder(View currentView , ClickListener clickListener) {
                super(currentView);
                itemName = currentView.findViewById(R.id.list_item_name);
                itemID = currentView.findViewById(R.id.list_item_id);
                itemPrice = currentView.findViewById(R.id.list_item_price);

                this.clickListener = clickListener;

                currentView.setOnClickListener(this);
            }

        @Override
        public void onClick(View v) {
            clickListener.onClick(mItems.get(getAdapterPosition()));
        }
    }
}
