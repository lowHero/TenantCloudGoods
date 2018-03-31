package com.nz2dev.tenantcloudgoods.data.repositories;

import com.nz2dev.tenantcloudgoods.data.api.room.EntityModelMapper;
import com.nz2dev.tenantcloudgoods.data.api.room.TenantCloudGoodsDatabase;
import com.nz2dev.tenantcloudgoods.data.api.room.dao.GoodsDao;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.GoodsEntity;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.repositories.GoodsWarehouse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

import static com.nz2dev.tenantcloudgoods.data.api.room.EntityModelMapper.toGoods;
import static com.nz2dev.tenantcloudgoods.data.api.room.EntityModelMapper.toModelsList;
import static com.nz2dev.tenantcloudgoods.data.api.room.ModelEntityMapper.toGoodsEntity;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Singleton
public class RoomGoodsWarehouse implements GoodsWarehouse {

    private final GoodsDao goodsDao;

    @Inject
    public RoomGoodsWarehouse(TenantCloudGoodsDatabase database) {
        goodsDao = database.getGoodsDao();
    }

    @Override
    public Single<Goods> add(Goods goods) {
        return Single.create(emitter -> {
            GoodsEntity entity = toGoodsEntity(goods);
            long generatedId = goodsDao.add(entity);

            goods.setId(generatedId);
            emitter.onSuccess(goods);
        });
    }

    @Override
    public Single<Goods> getGoods(long id) {
        return Single.create(emitter -> {
            GoodsEntity goodsEntity = goodsDao.getById(id);

            if (goodsEntity == null) {
                emitter.onSuccess(Goods.empty());
            } else {
                Goods goods = toGoods(goodsEntity);
                emitter.onSuccess(goods);
            }
        });
    }

    @Override
    public Single<List<Goods>> getAllGoods(long shopId) {
        return Single.create(emitter -> {
            List<GoodsEntity> entityList = goodsDao.getAllByShopId(shopId);
            List<Goods> goodsList = toModelsList(entityList, EntityModelMapper::toGoods);
            emitter.onSuccess(goodsList);
        });
    }
}
