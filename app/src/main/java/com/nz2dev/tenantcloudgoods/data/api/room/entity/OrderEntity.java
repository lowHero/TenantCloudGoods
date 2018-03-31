package com.nz2dev.tenantcloudgoods.data.api.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Entity(tableName = "orders")
public class OrderEntity {

    @PrimaryKey(autoGenerate = true)
    public final long id;
    public final long goodsId;
    public final int goodsAmount;
    public final float totalPrice;

    public OrderEntity(long id, long goodsId, int goodsAmount, float totalPrice) {
        this.id = id;
        this.goodsId = goodsId;
        this.goodsAmount = goodsAmount;
        this.totalPrice = totalPrice;
    }
}
