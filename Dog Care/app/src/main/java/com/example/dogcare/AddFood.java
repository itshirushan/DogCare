package com.example.dogcare;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogcare.databinding.ActivityAddFoodBinding;

public class AddFood extends AppCompatActivity {

    private ActivityAddFoodBinding binding;
    private AddFoodDBOpenHelper addFoodDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize database helper
        addFoodDBOpenHelper = new AddFoodDBOpenHelper(this);

        // Set button click listeners
        binding.btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFood.this, SeeProducts.class);
                startActivity(intent);
            }
        });

        binding.btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(v);
            }
        });
    }

    public void addData(View v) {
        SQLiteDatabase db = addFoodDBOpenHelper.getWritableDatabase();

        EditText foodId = findViewById(R.id.foodId);
        EditText name = findViewById(R.id.name);
        EditText brand = findViewById(R.id.brand);
        EditText price = findViewById(R.id.price);

        String foodIdValue = foodId.getText().toString();
        String nameText = name.getText().toString();
        String brandText = brand.getText().toString();
        String priceText = price.getText().toString();

        if (foodIdValue.isEmpty() || nameText.isEmpty() || brandText.isEmpty() || priceText.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();
            return;
        }

        int priceValue;
        try {
            priceValue = Integer.parseInt(priceText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_LONG).show();
            return;
        }

        addFoodDBOpenHelper.insertFood(db, foodIdValue, nameText, brandText, priceValue);

        Toast.makeText(this, "Record Added Successfully", Toast.LENGTH_LONG).show();
        db.close();
    }
}
