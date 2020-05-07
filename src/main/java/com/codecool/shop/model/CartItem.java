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
//            float subTotalPrice = cartitem.getProduct().getDefaultPrice() * cartitem.getQuantity();
            orderedItemsCount += cartitem.getQuantity();
//            totalPrice += subTotalPrice;
        }
        return orderedItemsCount;
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
