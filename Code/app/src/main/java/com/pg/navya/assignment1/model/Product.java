package com.pg.navya.assignment1.model;

import java.io.Serializable;

/**
 * Created by Reshma Salim on 23-04-2016.
 */
public class Product implements Serializable{
    private String name;
    private int imageId;
    private int price;

    public Product(String name, int imageId, int price) {
        this.name = name;
        this.imageId = imageId;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
