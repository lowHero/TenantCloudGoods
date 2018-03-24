package com.nz2dev.tenantcloudgoods.domain.interactors.shops;

import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.repositories.ShopRepository;

import javax.inject.Inject;

import dagger.Subcomponent;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Subcomponent
public class CreateShopUseCase {

    private ShopRepository shopRepository;

    @Inject
    public CreateShopUseCase(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void invoke(Shop shop) {
        shopRepository.createShop(shop);
    }

}
