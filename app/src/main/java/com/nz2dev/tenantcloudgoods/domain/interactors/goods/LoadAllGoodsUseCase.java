package com.nz2dev.tenantcloudgoods.domain.interactors.goods;

import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.repositories.GoodsWarehouse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 28.03.2018
 */
@Singleton
public class LoadAllGoodsUseCase {

    private final SchedulersManager schedulers;

    private final GoodsWarehouse goodsWarehouse;

    @Inject
    public LoadAllGoodsUseCase(SchedulersManager schedulers, GoodsWarehouse goodsWarehouse) {
        this.schedulers = schedulers;
        this.goodsWarehouse = goodsWarehouse;
    }

    public Single<List<Goods>> executor() {
        return goodsWarehouse.getAllGoods()
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
