package com.nz2dev.tenantcloudgoods.data.api.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    public final long id;
    public final String externalId;
    public final boolean admin;

    public UserEntity(long id, String externalId, boolean admin) {
        this.id = id;
        this.externalId = externalId;
        this.admin = admin;
    }

}
