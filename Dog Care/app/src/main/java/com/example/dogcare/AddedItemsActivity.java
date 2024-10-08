package com.example.dogcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogcare.databinding.ActivityAddedItemsBinding;

public class AddedItemsActivity extends AppCompatActivity {

    ActivityAddedItemsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddedItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerView = binding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Pass true to hide the "Add" button
        MyAdapter adapter = new MyAdapter(this, menu.addedItems, true);
        recyclerView.setAdapter(adapter);

        // Set up the footer food button click listener
        binding.footerFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddedItemsActivity.this, menu.class);
                startActivity(intent);
            }
        });

        // Set up the footer cart button click listener
        binding.footerCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddedItemsActivity.this, AddedItemsActivity.class);
                startActivity(intent);
            }
        });

        // Set up the footer profile button click listener
        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddedItemsActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

        // Set up the footer welcome button click listener
        binding.footerWelcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddedItemsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
