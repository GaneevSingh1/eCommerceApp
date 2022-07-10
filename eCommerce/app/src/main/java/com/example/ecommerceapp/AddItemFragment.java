package com.example.ecommerceapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerceapp.models.Item;

import java.util.List;

public class AddItemFragment extends Fragment {

    Button addItemButton;
    EditText itemName, itemPrice;

    public AddItemFragment() {
        super(R.layout.fragment_add_item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addItemButton = getView().findViewById(R.id.addItemButton);
        itemName = getView().findViewById(R.id.itemName);
        itemPrice = getView().findViewById(R.id.itemPrice);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item(0, 1, itemName.getText().toString(), 5, 4, 3, 2, 1, ItemStatus.DELIVERED, 0, 2, Integer.parseInt(itemPrice.getText().toString()));

                Database database = new Database(getContext());

                boolean success = database.addItem(item);

                List<Item> allItems = database.getAllItems();

                Toast.makeText(getContext(), allItems.get(0).getName() + " $" + allItems.get(0).getSellPrice(), Toast.LENGTH_SHORT).show();

                ItemsFragment.updateList();

                closeFragment();
            }
        });
    }

    protected void closeFragment(){
        getActivity().onBackPressed();
    }
}