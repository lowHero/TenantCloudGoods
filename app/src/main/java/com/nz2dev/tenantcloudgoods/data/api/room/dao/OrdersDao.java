package com.nz2dev.tenantcloudgoods.data.api.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.nz2dev.tenantcloudgoods.data.api.room.entity.CheckOrderMapping;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.OrderEntity;

import java.util.List;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Dao
public interface OrdersDao {

    @Insert
    long add(OrderEntity orderEntity);

    @Insert
    void addCheckOrderMapping(CheckOrderMapping checkOrderMapping);

    @Query("SELECT * FROM orders " +
            "INNER JOIN check_order ON check_order.orderId = orders.id " +
            "WHERE :checkId = check_order.checkId ")
    List<OrderEntity> getOrdersByCheckId(long checkId);

}
