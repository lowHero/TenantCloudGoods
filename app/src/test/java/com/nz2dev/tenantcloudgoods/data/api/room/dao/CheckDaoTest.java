package com.nz2dev.tenantcloudgoods.data.api.room.dao;

import android.arch.persistence.room.Room;

import com.nz2dev.tenantcloudgoods.data.api.room.TenantCloudGoodsDatabase;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.CheckEntity;

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
import static org.assertj.core.api.Assertions.filter;

/**
 * Created by nz2Dev on 31.03.2018
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class CheckDaoTest {

    private CheckDao checkDao;
    private TenantCloudGoodsDatabase database;

    @Before
    public void setUp() throws Exception {
        database = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application, TenantCloudGoodsDatabase.class)
                .allowMainThreadQueries()
                .build();

        checkDao = database.getCheckDao();
    }

    @After
    public void tearDown() {
        if (database != null) {
            database.close();
        }
    }

    @Test
    public void add() throws Exception {
        final long shopId = 1;
        final long userId = 2;
        final CheckEntity check = new CheckEntity(0, shopId, userId, new Date());

        final long checkId = checkDao.add(check);
        final List<CheckEntity> checkEntities = checkDao.getAllByUserId(userId);

        assertThat(checkId).isEqualTo(1);
        assertThat(checkEntities).hasSize(1);
        assertThat(checkEntities).has(new Condition<CheckEntity>() {
            @Override
            public boolean matches(CheckEntity value) {
                return value.id == 1
                        && value.shopId == check.shopId
                        && value.userId == check.userId;
            }
        }, Index.atIndex(0));
    }

}