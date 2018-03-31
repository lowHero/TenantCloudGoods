package com.nz2dev.tenantcloudgoods.data.api.room.dao;

import android.arch.persistence.room.Room;

import com.nz2dev.tenantcloudgoods.data.api.room.TenantCloudGoodsDatabase;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.CheckEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.ShopEntity;

import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by nz2Dev on 31.03.2018
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ShopDaoTest {

    private ShopDao shopDao;
    private TenantCloudGoodsDatabase database;

    @Before
    public void setUp() throws Exception {
        database = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application, TenantCloudGoodsDatabase.class)
                .allowMainThreadQueries()
                .build();

        shopDao = database.getShopDao();
    }

    @After
    public void tearDown() {
        if (database != null) {
            database.close();
        }
    }

    @Test
    public void add_checkByGettingAll_ShouldContainPreviouslyAdded() throws Exception {
        final ShopEntity shop = new ShopEntity(0, "Sp");

        final long shopId = shopDao.add(shop);
        final List<ShopEntity> shopEntities = shopDao.getAll();

        assertThat(shopId).isEqualTo(1);
        assertThat(shopEntities).hasSize(1);
        assertThat(shopEntities).has(new Condition<ShopEntity>() {
            @Override
            public boolean matches(ShopEntity value) {
                return value.id == 1
                        && value.name.equals(shop.name);
            }
        }, Index.atIndex(0));
    }

    @Test
    public void add_checkByGettingById_ShouldBeTheSameAsPreviouslyAdded() throws Exception {
        final ShopEntity shop = new ShopEntity(0, "Sp");

        final long shopId = shopDao.add(shop);
        final ShopEntity shopEntity = shopDao.getById(shopId);

        assertThat(shopId).isEqualTo(1);
        assertThat(shopEntity).has(new Condition<ShopEntity>() {
            @Override
            public boolean matches(ShopEntity value) {
                return value.id == 1
                        && value.name.equals(shop.name);
            }
        });
    }

}