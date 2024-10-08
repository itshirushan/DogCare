package com.example.dogcare;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogcare.databinding.ActivityProductlistBinding;

import java.util.ArrayList;

public class Productlist extends AppCompatActivity {

    private ActivityProductlistBinding binding;
    RecyclerView recyclerView;
    ArrayList<String> productid, productname, brandname, price;
    AddFoodDBOpenHelper DB;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DB = new AddFoodDBOpenHelper(this);
        productid = new ArrayList<>();
        productname = new ArrayList<>();
        brandname = new ArrayList<>();
        price = new ArrayList<>();

        recyclerView = binding.recyclerview; // Use binding to get RecyclerView
        adapter = new Adapter(this, productid, productname, brandname, price);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();

        // Set up button click listeners using binding
        binding.footerWelcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Productlist.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.footerFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Productlist.this, menu.class);
                startActivity(intent);
            }
        });

        binding.footerCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Productlist.this, Cart.class);
                startActivity(intent);
            }
        });

        binding.footerProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Productlist.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    private void displaydata() {
        Cursor cursor = DB.getFoodData();
        if (cursor.getCount() == 0) {
            Toast.makeText(Productlist.this, "No Records", Toast.LENGTH_SHORT).show();
            return;
        } else {
            productid.clear();
            productname.clear();
            brandname.clear();
            price.clear();
            while (cursor.moveToNext()) {
                productid.add(cursor.getString(1)); // Adjusted column index for productid
                productname.add(cursor.getString(2)); // Adjusted column index for productname
                brandname.add(cursor.getString(3)); // Adjusted column index for brandname
                price.add(cursor.getString(4)); // Adjusted column index for price
            }
            adapter.notifyDataSetChanged(); // Notify the adapter of data changes
        }
    }
}
