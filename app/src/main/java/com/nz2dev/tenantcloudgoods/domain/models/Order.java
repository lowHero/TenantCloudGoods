package com.nz2dev.tenantcloudgoods.domain.models;

import java.io.Serializable;

/**
 * Created by nz2Dev on 26.03.2018
 */
public class Order implements Serializable {

    public static Order createSingle(Goods goods) {
        return new Order(goods, 1);
    }

    private Goods goods;
    private int amount;

    public Order(Goods goods, int amount) {
        this.goods = goods;
        this.amount = amount;
    }

    public Goods getGoods() {
        return goods;
    }

    public int getAmount() {
        return amount;
    }
}
