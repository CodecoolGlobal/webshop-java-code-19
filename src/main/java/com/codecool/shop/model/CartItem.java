package com.codecool.shop.model;

import java.util.List;

public class CartItem {
    private int quantity;
    private Product product;

    public CartItem(int quantity, Product product) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getOrderedItemsCount(List<CartItem> data) {
        int orderedItemsCount = 0;
        for (CartItem cartitem : data) {
            orderedItemsCount += cartitem.getQuantity();
        }
        return orderedItemsCount;
    }

    public float getSubTotalPrice() {
        return this.getProduct().getDefaultPrice() * this.getQuantity();
    }

    public int getQuantity() {
        return quantity;
    }

    public void changeQuantity(int quantity) {
        this.quantity += quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
