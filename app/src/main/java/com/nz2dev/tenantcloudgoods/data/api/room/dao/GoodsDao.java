package com.nz2dev.tenantcloudgoods.data.api.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.nz2dev.tenantcloudgoods.data.api.room.entity.GoodsEntity;

import java.util.List;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Dao
public interface GoodsDao {

    @Insert
    long add(GoodsEntity goodsEntity);

    @Query("SELECT * FROM goods WHERE :id = goods.id")
    GoodsEntity getById(long id);

    @Query("SELECT * FROM goods WHERE :shopId = goods.shopId")
    List<GoodsEntity> getAllByShopId(long shopId);

}
