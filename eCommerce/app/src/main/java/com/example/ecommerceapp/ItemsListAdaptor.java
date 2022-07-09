package com.example.ecommerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemsListAdaptor extends RecyclerView.Adapter<ItemsListAdaptor.ViewHolder> {
        int mLayout;
        Context mContext;
        List<Item> mItems;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.itemID.setText(String.valueOf(mItems.get(position).getId()));
        viewHolder.itemName.setText(mItems.get(position).getName());
        viewHolder.itemPrice.setText(String.valueOf(mItems.get(position).getSellPrice()));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
            TextView itemID, itemName, itemPrice;

            private ViewHolder(View currentView) {
                super(currentView);
                itemName = currentView.findViewById(R.id.list_item_name);
                itemID = currentView.findViewById(R.id.list_item_id);
                itemPrice = currentView.findViewById(R.id.list_item_price);
            }
    }

    public ItemsListAdaptor(@NonNull Context context, int resource, @NonNull ArrayList<Item> items) {
        mContext = context;
        mLayout = resource;
        mItems = items;
    }
        //function to get our view that we are going  to be adapting our data to
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            //Get a reference to the current ListView item
//            View currentListViewItem = convertView;
//
//            // Check if the existing view is being reused, otherwise inflate the view
//            if (currentListViewItem == null) {
//                currentListViewItem = LayoutInflater.from(getContext()).inflate(mLayout, parent, false);
//            }
//
//            ViewHolder vh = new ViewHolder(currentListViewItem);
//
//            //Get the Number object for the current position
//            Item currentItem = mItems.get(position);
//
//            //Set the attributed of list_view_number_item views
//
//            vh.itemName.setText(currentItem.getName());
//            vh.itemID.setText(currentItem.getId());
//            vh.itemPrice.setText("$" + String.valueOf(currentItem.getSellPrice()));
//
//            return currentListViewItem;
//        }
}
