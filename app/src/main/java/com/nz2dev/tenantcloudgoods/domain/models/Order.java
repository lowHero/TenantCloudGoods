package com.nz2dev.tenantcloudgoods.domain.models;

import java.io.Serializable;

/**
 * Created by nz2Dev on 26.03.2018
 */
public class Order implements Serializable {

    public static Order createSingle(Goods goods) {
        return new Order(goods, 1, -1);
    }

    private Goods goods;
    private int goodsAmount;
    private float totalPrice;

    public Order(Goods goods, int goodsAmount, float totalPrice) {
        this.goods = goods;
        this.goodsAmount = goodsAmount;
        this.totalPrice = totalPrice;
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
