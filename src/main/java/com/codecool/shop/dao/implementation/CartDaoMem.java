package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDaoMem {
    private static CartDaoMem instance = null;
    private List<Product> order = new ArrayList<>();

    public CartDaoMem(){
        instance = this;
    }

    public void addToCart(Product product){
        order.add(product);
    }

    public List<Product> getOrder(){
        return order;
    }

    public static CartDaoMem getInstance(){
        if (instance == null){
            instance = new CartDaoMem();
        }
        return instance;
    }
    public HashMap<Product, Integer> prepareCartForDisplay() {
        HashMap<Product, Integer> preparedCart = new HashMap<Product, Integer>();

        for (Product item : order) {
            if (preparedCart.containsKey(item)) {
                int number = preparedCart.get(item);
                preparedCart.put(item, number + 1);
            } else {
                preparedCart.put(item, 1);
            }

        }
        return preparedCart;
    }

}
