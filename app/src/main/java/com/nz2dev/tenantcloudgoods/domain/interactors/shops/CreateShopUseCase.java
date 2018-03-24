package com.nz2dev.tenantcloudgoods.domain.interactors.shops;

import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.repositories.ShopRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class CreateShopUseCase {

    private final SchedulersManager schedulers;

    private ShopRepository shopRepository;

    @Inject
    CreateShopUseCase(SchedulersManager schedulers, ShopRepository shopRepository) {
        this.schedulers = schedulers;
        this.shopRepository = shopRepository;
    }

    public Single<Shop> executor(Shop shop) {
        return shopRepository.createShop(shop)
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
