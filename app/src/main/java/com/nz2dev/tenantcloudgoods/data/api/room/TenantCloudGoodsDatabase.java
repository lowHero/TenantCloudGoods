package com.nz2dev.tenantcloudgoods.data.api.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.nz2dev.tenantcloudgoods.data.api.room.dao.CheckDao;
import com.nz2dev.tenantcloudgoods.data.api.room.dao.GoodsDao;
import com.nz2dev.tenantcloudgoods.data.api.room.dao.OrdersDao;
import com.nz2dev.tenantcloudgoods.data.api.room.dao.ShopDao;
import com.nz2dev.tenantcloudgoods.data.api.room.dao.UserDao;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.CheckEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.CheckOrderMapping;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.GoodsEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.OrderEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.ShopEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.UserEntity;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Database(entities = {
        CheckEntity.class,
        CheckOrderMapping.class,
        GoodsEntity.class,
        OrderEntity.class,
        ShopEntity.class,
        UserEntity.class
}, version = 1)
public abstract class TenantCloudGoodsDatabase extends RoomDatabase {

    public abstract CheckDao getCheckDao();
    public abstract GoodsDao getGoodsDao();
    public abstract OrdersDao getOrdersDao();
    public abstract ShopDao getShopDao();
    public abstract UserDao getUserDao();

}
