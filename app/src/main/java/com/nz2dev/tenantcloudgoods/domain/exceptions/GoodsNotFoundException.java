package com.nz2dev.tenantcloudgoods.domain.exceptions;

/**
 * Created by nz2Dev on 25.03.2018
 */
public class GoodsNotFoundException extends RuntimeException {

    private final int id;

    public GoodsNotFoundException(int id) {
        super("id = " + id);
        this.id = id;
    }

    public int getGoodsId() {
        return id;
    }
}
