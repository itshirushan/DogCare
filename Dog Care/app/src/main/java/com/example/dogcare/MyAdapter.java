package com.example.dogcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyVeiwHolder> {

    Context context;
    List<item> items;
    OnAddClickListener onAddClickListener;
    boolean hideAddButton;


    public MyAdapter(Context context, List<item> items, boolean hideAddButton) {
        this.context = context;
        this.items = items;
        this.hideAddButton = hideAddButton;
    }

    @NonNull
    @Override
    public MyVeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVeiwHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyVeiwHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.priceView.setText(items.get(position).getPrice());
        holder.imageView.setImageResource(items.get(position).getImage());

        if (hideAddButton) {
            holder.addButton.setVisibility(View.GONE); // Hide the "Add" button
        } else {
            holder.addButton.setVisibility(View.VISIBLE); // Show the "Add" button
            holder.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAddClickListener != null) {
                        onAddClickListener.onAddClick(items.get(position));
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnAddClickListener(OnAddClickListener listener) {
        this.onAddClickListener = listener;
    }

    public interface OnAddClickListener {
        void onAddClick(item item);
    }
}
