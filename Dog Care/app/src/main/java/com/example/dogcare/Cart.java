package com.example.dogcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    List<item> items = new ArrayList<>();
    static List<item> addedItems = new ArrayList<>(); // To store added items globally
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        items.add(new item("John Wick", "100", R.drawable.home));
        items.add(new item("Jane Doe", "janedoe@gmail.com", R.drawable.home));
        // Add more items here...

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(getApplicationContext(), items, false); // Pass false to show the "Add" button
        recyclerView.setAdapter(adapter);

        // Handle the add button clicks
        adapter.setOnAddClickListener(new MyAdapter.OnAddClickListener() {
            @Override
            public void onAddClick(item item) {
                addedItems.add(item); // Add the clicked item to the list
            }
        });

        Button showAddedItemsButton = findViewById(R.id.show_added_items_button);
        showAddedItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, AddedItemsActivity.class);
                startActivity(intent);
            }
        });
    }
}
