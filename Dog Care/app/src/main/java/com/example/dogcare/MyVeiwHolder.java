package com.example.dogcare;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class MyVeiwHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView, priceView;
    Button addButton;

    public MyVeiwHolder(@NotNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        nameView = itemView.findViewById(R.id.name);
        priceView = itemView.findViewById(R.id.price);
        addButton = itemView.findViewById(R.id.add_button); // Reference to the "Add" button
    }
}
