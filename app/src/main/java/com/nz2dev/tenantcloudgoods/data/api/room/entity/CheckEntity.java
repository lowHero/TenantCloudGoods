package com.nz2dev.tenantcloudgoods.data.api.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.nz2dev.tenantcloudgoods.data.api.room.converters.DateConverter;

import java.util.Date;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Entity(tableName = "checks")
public class CheckEntity {

    @PrimaryKey(autoGenerate = true)
    public final long id;
    public final long userId;
    public final long shopId;

    @TypeConverters(DateConverter.class)
    public final Date time;

    public CheckEntity(long id, long shopId, long userId, Date time) {
        this.id = id;
        this.shopId = shopId;
        this.userId = userId;
        this.time = time;
    }

}
