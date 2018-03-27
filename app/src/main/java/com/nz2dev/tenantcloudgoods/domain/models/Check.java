package com.nz2dev.tenantcloudgoods.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nz2Dev on 25.03.2018
 */
public class Check implements Serializable {

    private Shop shop;
    private List<Order> orders;
    private float price;

    public Check(Shop shop, List<Order> orders, float price) {
        this.shop = shop;
        this.orders = orders;
        this.price = price;
    }

    public Shop getShop() {
        return shop;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public float getPrice() {
        return price;
    }

}
