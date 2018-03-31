package com.nz2dev.tenantcloudgoods.data.api.room.dao;

import android.arch.persistence.room.Room;

import com.nz2dev.tenantcloudgoods.data.api.room.TenantCloudGoodsDatabase;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.GoodsEntity;

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
public class GoodsDaoTest {

    private GoodsDao goodsDao;
    private TenantCloudGoodsDatabase database;

    @Before
    public void setUp() throws Exception {
        database = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application, TenantCloudGoodsDatabase.class)
                .allowMainThreadQueries()
                .build();

        goodsDao = database.getGoodsDao();
    }

    @After
    public void tearDown() {
        if (database != null) {
            database.close();
        }
    }

    @Test
    public void add_checkByGettingById_ShouldBeTheSameAsPreviouslyAdded() throws Exception {
        final GoodsEntity goods = new GoodsEntity(0, 1, "G1", null, 1.5f, 1);

        final long goodsId = goodsDao.add(goods);
        final GoodsEntity goodsEntityFromDB = goodsDao.getById(goodsId);

        assertThat(goodsId).isEqualTo(1);
        assertThat(goodsEntityFromDB).has(new Condition<GoodsEntity>() {
            @Override
            public boolean matches(GoodsEntity value) {
                return value.id == 1
                        && value.name.equals(goods.name)
                        && value.imageUrl == goods.imageUrl
                        && value.price == goods.price
                        && value.availableAmount == goods.availableAmount;
            }
        });
    }

    @Test
    public void add_checkByAllForShop_ShouldBeTheSameAsPreviouslyAdded() throws Exception {
        final long shopIdToAdd = 2;
        final GoodsEntity goods = new GoodsEntity(0, shopIdToAdd, "G1", null, 1.5f, 1);

        final long goodsId = goodsDao.add(goods);
        final List<GoodsEntity> goodsEntitiesFromDB = goodsDao.getAllByShopId(shopIdToAdd);

        assertThat(goodsId).isEqualTo(1);
        assertThat(goodsEntitiesFromDB).has(new Condition<GoodsEntity>() {
            @Override
            public boolean matches(GoodsEntity value) {
                return value.id == 1
                        && value.name.equals(goods.name)
                        && value.imageUrl == goods.imageUrl
                        && value.price == goods.price
                        && value.availableAmount == goods.availableAmount;
            }
        }, Index.atIndex(0));
    }

}