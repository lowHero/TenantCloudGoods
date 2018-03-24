package com.nz2dev.tenantcloudgoods.domain.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.Shop;

import java.util.List;

/**
 * Created by nz2Dev on 24.03.2018
 */
public interface ShopRepository {

    void createShop(Shop shop);
    List<Shop> getAllShops();

}
