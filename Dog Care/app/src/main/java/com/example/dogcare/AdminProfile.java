package com.example.dogcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogcare.databinding.ActivityAdminProfileBinding;
import com.example.dogcare.databinding.ActivityMainBinding;

public class AdminProfile extends AppCompatActivity {

    ActivityAdminProfileBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize binding
        binding = ActivityAdminProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize database helper if needed
        databaseHelper = new DatabaseHelper(this);

        // Set up the welcome add pet button click listener
        binding.AddPetFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProfile.this, AddFood.class);
                startActivity(intent);
            }
        });

        binding.viewPetFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProfile.this, SeeProducts.class);
                startActivity(intent);
            }
        });

    }
}
