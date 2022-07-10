package com.example.ecommerceapp;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.Interfaces.ClickListener;
import com.example.ecommerceapp.models.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ItemsFragment extends Fragment implements ClickListener {

    Database database;
    FloatingActionButton floatingActionButton;
    static ItemsListAdaptor listAdaptor;

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

        listAdaptor = new ItemsListAdaptor(getContext(), R.layout.list_item, allItems, this);
        listAdaptor.setFragment(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = getView().findViewById(R.id.items_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdaptor);

        floatingActionButton = getView().findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragment = getActivity().getSupportFragmentManager();

                fragment.beginTransaction()
                        .replace(R.id.fragment_container_view, AddItemFragment.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public static void updateList(){
        listAdaptor.notifyDataSetChanged();
    }

    @Override
    public void onClick(Item item) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        ItemDetailsFragment fragment = new ItemDetailsFragment(item);

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment, null)
                .addToBackStack(null)
                .commit();
    }
}