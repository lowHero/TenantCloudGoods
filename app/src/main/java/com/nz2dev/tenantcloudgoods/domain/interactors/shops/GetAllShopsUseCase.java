package com.nz2dev.tenantcloudgoods.domain.interactors.shops;

import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.repositories.ShopRepository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class GetAllShopsUseCase {

    private final ShopRepository shopRepository;

    @Inject
    public GetAllShopsUseCase(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> invoke() {
        List<Shop> shops = shopRepository.getAllShops();
        return shops == null ? Collections.emptyList() : shops;
    }

}
