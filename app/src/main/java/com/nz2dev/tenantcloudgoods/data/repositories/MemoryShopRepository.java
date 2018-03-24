package com.nz2dev.tenantcloudgoods.data.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.repositories.ShopRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class MemoryShopRepository implements ShopRepository {

    private List<Shop> shops = new ArrayList<>();

    @Inject
    public MemoryShopRepository() {
    }

    @Override
    public Single<Shop> createShop(Shop shop) {
        shops.add(shop);
        return Single.just(shop);
    }

    @Override
    public Single<List<Shop>> getAllShops() {
        return Single.just(new ArrayList<>(shops));
    }

}
