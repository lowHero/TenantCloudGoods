package com.nz2dev.tenantcloudgoods.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nz2Dev on 26.03.2018
 */
public class Order implements Serializable {

    public static float priceOf(List<Order> orders) {
        float totalPrice = 0f;
        for (Order order : orders) {
            totalPrice += order.getTotalPrice();
        }
        return totalPrice;
    }

    public static Order createSingle(Goods goods) {
        return new Order(0, goods, 1, -1);
    }

    private long id;
    private Goods goods;
    private int goodsAmount;
    private float totalPrice;

    public Order(long id, Goods goods, int goodsAmount, float totalPrice) {
        this.id = id;
        this.goods = goods;
        this.goodsAmount = goodsAmount;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void set(Order order) {
        if (this.equals(order)) {
            return;
        }
        setGoods(order.getGoods());
        setGoodsAmount(order.getGoodsAmount());
        setTotalPrice(order.getTotalPrice());
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoodsAmount(int goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public int getGoodsAmount() {
        return goodsAmount;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
