package com.nz2dev.tenantcloudgoods.domain.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by nz2Dev on 25.03.2018
 */
public class Check implements Serializable {

    public static Check create(Shop shop, List<Order> orders) {
        return new Check(0, shop, new Date(), orders);
    }

    private long id;
    private Shop shop;
    private Date time;
    private List<Order> orders;

    public Check(long id, Shop shop, Date time, List<Order> orders) {
        this.id = id;
        this.shop = shop;
        this.time = time;
        this.orders = orders;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Shop getShop() {
        return shop;
    }

    public Date getTime() {
        return time;
    }

    public List<Order> getOrders() {
        return orders;
    }

}
