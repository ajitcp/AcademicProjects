package com.info6250.finalproject.pojo;

import java.util.ArrayList;
import java.util.List;

public class Cart {
private List<Item> cart;
    
    public Cart() {
        cart = new ArrayList<>();
    }
    
    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }
    
    public Item findItem(String name) {
        for (Item item : cart) {
            if (name.equals(item.getName())) {
                System.out.println("match true");
                return item;
            }
        }
        return null;
    }
    
    public void addItem(Item item) {
        cart.add(item);
    }
    
    public void deleteItem(Item item) {
        cart.remove(item);
    }
    
    public void deleteItem(String name) {
        Item item = findItem(name);
        if (item != null)
            deleteItem(item);
    }
    
    public void modifyItemCount(String name, int count) {
        for (Item tmp : cart) {
            if (name.equals(tmp.getName())) {
                tmp.setCount(count);
            }
        }
    }

}
