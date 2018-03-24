package com.nz2dev.tenantcloudgoods.domain.interactors.shops;

import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.repositories.ShopRepository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class GetAllShopsUseCase {

    private final SchedulersManager schedulers;

    private final ShopRepository shopRepository;

    @Inject
    public GetAllShopsUseCase(SchedulersManager schedulers, ShopRepository shopRepository) {
        this.schedulers = schedulers;
        this.shopRepository = shopRepository;
    }

    public Single<List<Shop>> executor() {
        return shopRepository.getAllShops()
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
