package com.example.dogcare;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dogcare.databinding.ActivityViewArticleBinding;

public class ViewArticleActivity extends AppCompatActivity {

    private ActivityViewArticleBinding binding;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Initialize the binding object
        binding = ActivityViewArticleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new DatabaseHelper(this);

        displayArticles();

        // Set up footer buttons click listeners
        binding.footerWelcomeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ViewArticleActivity.this, MainActivity.class);
            startActivity(intent);
        });

        binding.footerFoodBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ViewArticleActivity.this, Productlist.class);
            startActivity(intent);
        });

        binding.footerCartBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ViewArticleActivity.this, Cart.class);
            startActivity(intent);
        });

        binding.footerProfileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ViewArticleActivity.this, UserProfileActivity.class);
            startActivity(intent);
        });
    }

    private void displayArticles() {
        Cursor cursor = db.getAllArticles();
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String link = cursor.getString(cursor.getColumnIndex("link"));

                View articleView = LayoutInflater.from(this).inflate(R.layout.article_item, null);
                TextView titleView = articleView.findViewById(R.id.article_title);
                TextView descriptionView = articleView.findViewById(R.id.article_description);
                TextView linkView = articleView.findViewById(R.id.article_link);

                titleView.setText(title);
                descriptionView.setText(description);
                linkView.setText(link);

                binding.articleContainer.addView(articleView);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
