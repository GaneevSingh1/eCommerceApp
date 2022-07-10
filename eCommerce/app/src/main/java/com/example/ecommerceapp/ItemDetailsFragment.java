package com.example.ecommerceapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerceapp.models.Item;

public class ItemDetailsFragment extends Fragment {

    protected Database database;
    protected TextView itemID, itemName, itemPrice;
    protected Item item;

    public ItemDetailsFragment(Item item) {
        super(R.layout.fragment_item_details);
        this.item = item;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new Database(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemName = getView().findViewById(R.id.details_item_name);
        itemID = getView().findViewById(R.id.details_item_id);
        itemPrice = getView().findViewById(R.id.details_item_price);

        itemID.setText(String.valueOf(item.getId()));
        itemName.setText(item.getName());
       itemPrice.setText(String.valueOf(item.getSellPrice()));
    }
}