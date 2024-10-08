package com.example.dogcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogcare.databinding.ActivityMenuBinding;

import java.util.ArrayList;
import java.util.List;

public class menu extends AppCompatActivity {

    ActivityMenuBinding binding;

    List<item> items = new ArrayList<>();
    static List<item> addedItems = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Initialize binding
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = binding.recyclerview;

        items.add(new item("Premium puppy", "Rs.550", R.drawable.premium));
        items.add(new item("Drools Adult Dog Food", "Rs.1600", R.drawable.drools));
        items.add(new item("Classic Pets", "Rs.680", R.drawable.classic));
        items.add(new item("Pedigree", "Rs.2300", R.drawable.pedigree));
        items.add(new item("Meat Up", "Rs.3000", R.drawable.meat));
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

        Button showAddedItemsButton = binding.showAddedItemsButton;
        showAddedItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, AddedItemsActivity.class);
                startActivity(intent);
            }
        });

        // Set up the footer food button click listener
        binding.footerFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, menu.class);
                startActivity(intent);
            }
        });

        // Set up the footer cart button click listener
        binding.footerCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, AddedItemsActivity.class);
                startActivity(intent);
            }
        });

        // Set up the profile button click listener
        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

        // Set up the welcome button click listener
        binding.footerWelcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
