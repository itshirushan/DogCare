package com.example.dogcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private ArrayList product_id, product_name, brand_name, price;

    public Adapter(Context context, ArrayList product_id, ArrayList product_name, ArrayList brand_name, ArrayList price) {
        this.context = context;
        this.product_id = product_id;
        this.product_name = product_name;
        this.brand_name = brand_name;
        this.price = price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.products,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.product_id.setText(String.valueOf(product_id.get(position)));
        holder.product_name.setText(String.valueOf(product_name.get(position)));
        holder.brand_name.setText(String.valueOf(brand_name.get(position)));
        holder.price.setText(String.valueOf(price.get(position)));


    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView product_id, product_name, brand_name, price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id = itemView.findViewById(R.id.productid);
            product_name = itemView.findViewById(R.id.productname);
            brand_name = itemView.findViewById(R.id.brandname);
            price = itemView.findViewById(R.id.price);


        }

    }
}
