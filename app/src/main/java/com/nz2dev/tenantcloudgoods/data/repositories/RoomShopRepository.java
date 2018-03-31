package com.nz2dev.tenantcloudgoods.data.repositories;

import com.nz2dev.tenantcloudgoods.data.api.room.EntityModelMapper;
import com.nz2dev.tenantcloudgoods.data.api.room.ModelEntityMapper;
import com.nz2dev.tenantcloudgoods.data.api.room.TenantCloudGoodsDatabase;
import com.nz2dev.tenantcloudgoods.data.api.room.dao.ShopDao;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.ShopEntity;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.repositories.ShopRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

import static com.nz2dev.tenantcloudgoods.data.api.room.EntityModelMapper.toModelsList;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Singleton
public class RoomShopRepository implements ShopRepository {

    private final ShopDao shopDao;

    @Inject
    public RoomShopRepository(TenantCloudGoodsDatabase database) {
        shopDao = database.getShopDao();
    }

    @Override
    public Single<Shop> createShop(Shop shop) {
        return Single.create(emitter -> {
            ShopEntity entity = ModelEntityMapper.toShopEntity(shop);
            long generatedId = shopDao.add(entity);

            shop.setId(generatedId);
            emitter.onSuccess(shop);
        });
    }

    @Override
    public Single<List<Shop>> getAllShops() {
        return Single.create(emitter -> {
            List<ShopEntity> entitiesList = shopDao.getAll();
            List<Shop> shopsList = toModelsList(entitiesList, EntityModelMapper::toShop);
            emitter.onSuccess(shopsList);
        });
    }
}
