package com.nz2dev.tenantcloudgoods.data.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.repositories.ShopRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

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
    public void createShop(Shop shop) {
        shops.add(shop);
    }

    @Override
    public List<Shop> getAllShops() {
        return shops;
    }

}
