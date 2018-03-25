package com.nz2dev.tenantcloudgoods.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nz2Dev on 25.03.2018
 */
public class Check implements Serializable {

    private List<Order> orders;
    private float amount;

    public Check(List<Order> orders, float amount) {
        this.orders = orders;
        this.amount = amount;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public float getAmount() {
        return amount;
    }

}
