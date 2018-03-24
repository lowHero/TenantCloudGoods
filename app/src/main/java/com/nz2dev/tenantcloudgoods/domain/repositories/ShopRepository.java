package com.nz2dev.tenantcloudgoods.domain.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.Shop;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
public interface ShopRepository {

    Single<Shop> createShop(Shop shop);
    Single<List<Shop>> getAllShops();

}
