package com.nz2dev.tenantcloudgoods.data.api.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.nz2dev.tenantcloudgoods.data.api.room.entity.CheckEntity;

import java.util.List;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Dao
public interface CheckDao {

    @Insert
    long add(CheckEntity checkEntity);

    @Query("SELECT * FROM checks WHERE :userId = checks.userId")
    List<CheckEntity> getAllByUserId(long userId);

}
