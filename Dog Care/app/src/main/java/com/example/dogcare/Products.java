package com.example.dogcare;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogcare.databinding.ProductsBinding;

public class Products extends AppCompatActivity {
    ProductsBinding binding;
    AddFoodDBOpenHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new AddFoodDBOpenHelper(this);

        Button addToCartButton = binding.addtocart;
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });
    }

    private void addToCart() {
        TextView productIdView = binding.productid;
        TextView productNameView = binding.productname;
        TextView brandNameView = binding.brandname;
        TextView priceView = binding.price;

        String productId = productIdView.getText().toString();
        String productName = productNameView.getText().toString();
        String brandName = brandNameView.getText().toString();
        String price = priceView.getText().toString();

        SQLiteDatabase db = null;
        try {
            db = databaseHelper.getWritableDatabase();
            boolean isInserted = databaseHelper.insertToCart(db, productId, productName, brandName, price);
            if (isInserted) {
                Toast.makeText(this, "Successfully added to the cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Data saving failed", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }


}
