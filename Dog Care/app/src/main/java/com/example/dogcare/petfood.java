package com.example.dogcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogcare.databinding.ActivityMainBinding;
import com.example.dogcare.databinding.ActivityPetfoodBinding;

public class petfood extends AppCompatActivity {

    ActivityPetfoodBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPetfoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.footerWelcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(petfood.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}