package com.example.dogcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogcare.databinding.ActivityArticleTypeBinding;

public class articleTypeActivity extends AppCompatActivity {

    private ActivityArticleTypeBinding binding; // Correct binding type
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticleTypeBinding.inflate(getLayoutInflater()); // Inflate the layout with binding
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.AddArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleTypeActivity.this, AddArticleActivity.class);
                startActivity(intent);
            }
        });

        binding.viewArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleTypeActivity.this, ViewArticleActivity.class);
                startActivity(intent);
            }
        });

        binding.footerFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleTypeActivity.this, menu.class);
                startActivity(intent);
            }
        });

        binding.footerCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleTypeActivity.this, AddedItemsActivity.class);
                startActivity(intent);
            }
        });

        binding.footerWelcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleTypeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleTypeActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}
