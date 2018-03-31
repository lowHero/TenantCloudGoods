package com.nz2dev.tenantcloudgoods.data.api.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Entity(tableName = "check_order")
public class CheckOrderMapping {

    public static CheckOrderMapping create(long checkId, long orderId) {
        return new CheckOrderMapping(0, checkId, orderId);
    }

    @PrimaryKey(autoGenerate = true)
    public final long id;

    @ForeignKey(entity = CheckEntity.class, parentColumns = "id", childColumns = "checkId", onDelete = CASCADE)
    public final long checkId;
    public final long orderId;

    public CheckOrderMapping(long id, long checkId, long orderId) {
        this.id = id;
        this.checkId = checkId;
        this.orderId = orderId;
    }

}
