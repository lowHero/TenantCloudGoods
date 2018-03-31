package com.nz2dev.tenantcloudgoods.domain.models;

import java.io.Serializable;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class Goods implements Serializable {

    public static Goods empty() {
        return new Goods(-1, -1, null, null, -1f, -1);
    }

    public static boolean isEmpty(Goods goods) {
        return goods.id == -1
                && goods.shopId == -1
                && goods.imageUrl == null
                && Float.compare(-1f, goods.getPrice()) == 0
                && goods.availableAmount == -1;
    }

    public static Goods emptyIdHolder(long id) {
        return new Goods(id, -1, null, null, -1f, -1);
    }

    public static boolean isEmptyIdHolder(Goods goods) {
        return goods.name == null
                && goods.shopId == -1
                && goods.imageUrl == null
                && Float.floatToIntBits(goods.price) == Float.floatToIntBits(-1f)
                && goods.availableAmount == -1;
    }

    private long id;
    private long shopId;
    private String name;
    private String imageUrl;
    private float price;
    private int availableAmount;

    public Goods(long id, long shopId, String name, String imageUrl, float price, int availableAmount) {
        this.id = id;
        this.shopId = shopId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.availableAmount = availableAmount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public long getId() {
        return id;
    }

    public long getShopId() {
        return shopId;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getPrice() {
        return price;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

        if (id != goods.id) return false;
        if (shopId != goods.shopId) return false;
        if (Float.compare(goods.price, price) != 0) return false;
        if (availableAmount != goods.availableAmount) return false;
        if (name != null ? !name.equals(goods.name) : goods.name != null) return false;
        return imageUrl != null ? imageUrl.equals(goods.imageUrl) : goods.imageUrl == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (shopId ^ (shopId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + availableAmount;
        return result;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", availableAmount=" + availableAmount +
                '}';
    }

}
