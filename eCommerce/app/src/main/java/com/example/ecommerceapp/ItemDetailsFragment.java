package com.example.ecommerceapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.models.Item;

public class ItemDetailsFragment extends Fragment {

    protected Database database;
    protected TextView itemID, itemName, itemSellPrice, itemOrderNumber, itemStatus, itemQuantityOrdered, ItemInStock, itemSold, itemDamaged, itemWrittenOff, itemPurchasePrice, itemShippingCost, itemTotalCost;
    protected Item item;
    protected Button deleteButton;

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
        itemSellPrice = getView().findViewById(R.id.details_item_sell_price);
        itemOrderNumber = getView().findViewById(R.id.details_order_number);
        itemStatus = getView().findViewById(R.id.details_item_status);
        itemQuantityOrdered = getView().findViewById(R.id.details_table_quantity_ordered);
        ItemInStock = getView().findViewById(R.id.details_table_in_stock);
        itemSold = getView().findViewById(R.id.details_table_sold);
        itemDamaged = getView().findViewById(R.id.details_table_damaged);
        itemWrittenOff = getView().findViewById(R.id.details_table_written_off);
        itemPurchasePrice = getView().findViewById(R.id.details_table_purchase_price);
        itemShippingCost = getView().findViewById(R.id.details_table_shipping_cost);
        itemTotalCost = getView().findViewById(R.id.details_table_total_cost);
        deleteButton = getView().findViewById(R.id.details_item_delete_button);

        itemID.setText("ID: " + item.getId());
        itemName.setText("Name: " + item.getName());
        itemSellPrice.setText("Selling Price: $" +item.getSellPrice());
        itemOrderNumber.setText("Order Number: " + item.getOrderNumber());
        itemStatus.setText("Status: " + ItemStatus.statusToString(item.getItemsStatus()));
        itemQuantityOrdered.setText(String.valueOf(item.getQuantityOrdered()));
        ItemInStock.setText(String.valueOf(item.getNumInStock()));
        itemSold.setText(String.valueOf(item.getNumSold()));
        itemDamaged.setText(String.valueOf(item.getNumDamaged()));
        itemWrittenOff.setText(String.valueOf(item.getNumWrittenOff()));
        itemPurchasePrice.setText("$" + item.getPurchasePrice());
        itemShippingCost.setText("$" + item.getShippingPerItem());
        itemTotalCost.setText("$" + item.getTotalCostPerItem());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean successful = database.deleteItem(item);
                Toast.makeText(getContext(), "Succesfully Deleted: " + successful.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}