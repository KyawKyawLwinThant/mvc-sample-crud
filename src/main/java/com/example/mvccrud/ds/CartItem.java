package com.example.mvccrud.ds;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.Objects;

/*
id,
title,
price,
quantity
 */
@Getter
@Setter
@ToString
public class CartItem {
    private int id;
    private String title;
    private double price;
    private int quantity;

    public CartItem(){}

    private boolean render;
    private LinkedList<Integer> quantityLinkedList=
            new LinkedList<>();

    public CartItem(int id, String title, double price, int quantity) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem cartItem)) return false;
        return id == cartItem.id && title.equals(cartItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
