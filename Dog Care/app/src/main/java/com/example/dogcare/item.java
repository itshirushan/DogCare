package com.example.dogcare;

public class item {
    private String name;
    private String price;
    private int image;

    // Constructor, getters, and setters
    public item(String name, String price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
