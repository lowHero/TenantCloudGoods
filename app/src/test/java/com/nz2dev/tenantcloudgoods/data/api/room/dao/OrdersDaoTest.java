package com.nz2dev.tenantcloudgoods.data.api.room.dao;

import android.arch.persistence.room.Room;

import com.nz2dev.tenantcloudgoods.data.api.room.TenantCloudGoodsDatabase;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.CheckEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.CheckOrderMapping;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.OrderEntity;

import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by nz2Dev on 31.03.2018
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class OrdersDaoTest {

    private OrdersDao ordersDao;
    private TenantCloudGoodsDatabase database;

    @Before
    public void setUp() throws Exception {
        database = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application, TenantCloudGoodsDatabase.class)
                .allowMainThreadQueries()
                .build();

        ordersDao = database.getOrdersDao();
    }

    @After
    public void tearDown() {
        if (database != null) {
            database.close();
        }
    }

    @Test
    public void add() throws Exception {
        long checkId = 100;
        final OrderEntity order = new OrderEntity(0, 1, 2, 4f);

        final long orderId = ordersDao.add(order);
        ordersDao.addCheckOrderMapping(CheckOrderMapping.create(checkId, orderId));

        final List<OrderEntity> orderEntities = ordersDao.getOrdersByCheckId(checkId);

        assertThat(orderId).isEqualTo(1);
        assertThat(orderEntities).hasSize(1);
        assertThat(orderEntities).has(new Condition<OrderEntity>() {
            @Override
            public boolean matches(OrderEntity value) {
                return value.id == 1
                        && value.goodsId == order.goodsId
                        && value.goodsAmount == order.goodsAmount
                        && value.totalPrice == order.totalPrice;
            }
        }, Index.atIndex(0));
    }

}