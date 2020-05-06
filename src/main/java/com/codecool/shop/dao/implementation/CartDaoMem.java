package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDaoMem {
    private static CartDaoMem instance = null;
//    private List<Product> order = new ArrayList<>();
    private List<CartItem> data = new ArrayList<>();

    public CartDaoMem(){
        instance = this;
    }

    public void addToCart(CartItem product){
        data.add(product);
    }

    public List<CartItem> getProducts() {
        return data;
    }

    public CartItem find(int id) {
        return data.stream().filter(t -> t.getProduct().getId() == id).findFirst().orElse(null);
    }


    public static CartDaoMem getInstance(){
        if (instance == null){
            instance = new CartDaoMem();
        }
        return instance;
    }

}
