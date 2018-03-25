package com.nz2dev.tenantcloudgoods.domain.models;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class Goods {

    public static Goods emptyIdHolder(int id) {
        return new Goods(id, null, null, -1);
    }

    public static boolean isEmptyIdHolder(Goods goods) {
        return goods.name == null && goods.imageUrl == null && goods.availableAmount == -1;
    }

    private int id;
    private String name;
    private String imageUrl;
    private int availableAmount;

    public Goods(int id, String name, String imageUrl, int availableAmount) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.availableAmount = availableAmount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
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
        if (availableAmount != goods.availableAmount) return false;
        if (name != null ? !name.equals(goods.name) : goods.name != null) return false;
        return imageUrl != null ? imageUrl.equals(goods.imageUrl) : goods.imageUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + availableAmount;
        return result;
    }
}
