package com.nz2dev.tenantcloudgoods.data.api.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Entity(tableName = "goods")
public class GoodsEntity {

    @PrimaryKey(autoGenerate = true)
    public final long id;

    @ForeignKey(entity = ShopEntity.class, parentColumns = "id", childColumns = "shopId", onDelete = CASCADE)
    public final long shopId;

    public final String name;
    public final String imageUrl;
    public final float price;
    public final int availableAmount;

    public GoodsEntity(long id, long shopId, String name, String imageUrl, float price, int availableAmount) {
        this.id = id;
        this.shopId = shopId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.availableAmount = availableAmount;
    }

}
