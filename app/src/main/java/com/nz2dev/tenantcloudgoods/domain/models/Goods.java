package com.nz2dev.tenantcloudgoods.domain.models;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class Goods {

    private int id;
    private String name;
    private String imageUrl;
    private int avaliableAmount;

    public Goods(int id, String name, String imageUrl, int avaliableAmount) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.avaliableAmount = avaliableAmount;
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

    public int getAvaliableAmount() {
        return avaliableAmount;
    }
}
