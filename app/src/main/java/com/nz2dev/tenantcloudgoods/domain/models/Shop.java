package com.nz2dev.tenantcloudgoods.domain.models;

import java.io.Serializable;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class Shop implements Serializable {

    public static Shop createFromName(String name) {
        return new Shop(0, name);
    }

    private long id;
    private String name;

    public Shop(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
