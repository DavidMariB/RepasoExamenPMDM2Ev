package com.dmb.repasoexamenpmdm2ev.Models;

/**
 * Created by davidmari on 28/1/18.
 */

public class Product {
    String name,price,description;

    public Product(String name, String price, String description){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
