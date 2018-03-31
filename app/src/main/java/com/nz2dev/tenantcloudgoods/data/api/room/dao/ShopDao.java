package com.nz2dev.tenantcloudgoods.data.api.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.nz2dev.tenantcloudgoods.data.api.room.entity.ShopEntity;

import java.util.List;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Dao
public interface ShopDao {

    @Insert
    long add(ShopEntity shopEntity);

    @Query("SELECT * FROM shops WHERE :id = shops.id")
    ShopEntity getById(long id);

    @Query("SELECT * FROM shops")
    List<ShopEntity> getAll();

}
