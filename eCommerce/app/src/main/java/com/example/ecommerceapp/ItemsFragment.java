package com.example.ecommerceapp;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.models.Item;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ItemsFragment extends Fragment{

    Database database;

    Button addItemButton;
    EditText itemName, itemPrice;

    public ItemsFragment() {
        super(R.layout.fragment_items);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new Database(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Item> allItems = database.getAllItems();

        ItemsListAdaptor listAdaptor = new ItemsListAdaptor(getContext(), R.layout.list_item, allItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = getView().findViewById(R.id.items_recycler_view);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdaptor);


//        itemName = getView().findViewById(R.id.addItemButton);
//        itemName = getView().findViewById(R.id.itemName);
//        itemPrice = getView().findViewById(R.id.itemPrice);

//        addItemButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Item item = new Item(0, 1, itemName.getText().toString(), 5, 4, 3, 2, 1, ItemStatus.DELIVERED, 0, 2, Integer.parseInt(itemPrice.getText().toString()));
//
//                Database database = new Database(getContext());
//
//                boolean success = database.addItem(item);
//
//                List<Item> allItems = database.getAllItems();
//
//                Toast.makeText(getContext(), allItems.get(0).getName() + " $" + allItems.get(0).getSellPrice(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}