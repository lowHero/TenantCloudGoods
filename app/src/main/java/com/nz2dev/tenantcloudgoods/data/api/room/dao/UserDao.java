package com.nz2dev.tenantcloudgoods.data.api.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.nz2dev.tenantcloudgoods.data.api.room.entity.UserEntity;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Dao
public interface UserDao {

    @Insert
    long add(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE :externalId = users.externalId")
    UserEntity getByExternalId(String externalId);

}
