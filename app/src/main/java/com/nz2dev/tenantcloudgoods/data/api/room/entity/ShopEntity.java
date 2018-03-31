package com.nz2dev.tenantcloudgoods.data.api.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Entity(tableName = "shops")
public class ShopEntity {

    @PrimaryKey(autoGenerate = true)
    public final long id;
    public final String name;

    public ShopEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
