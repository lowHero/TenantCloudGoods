package com.nz2dev.tenantcloudgoods.domain.interactors.goods;

import com.nz2dev.tenantcloudgoods.domain.exceptions.GoodsEmptyDataException;
import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.repositories.GoodsWarehouse;
import com.nz2dev.tenantcloudgoods.domain.tools.Serializer;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 28.03.2018
 */
@Singleton
public class CreateGoodsByScannedDataUseCase {

    private final SchedulersManager schedulers;

    private final GoodsWarehouse goodsWarehouse;
    private final Serializer serializer;

    @Inject
    public CreateGoodsByScannedDataUseCase(SchedulersManager schedulers, GoodsWarehouse goodsWarehouse, Serializer serializer) {
        this.schedulers = schedulers;
        this.goodsWarehouse = goodsWarehouse;
        this.serializer = serializer;
    }

    public Single<Goods> executor(Shop shop, String scanData) {
        return Single.just(scanData)
                .map(serializer::deserializeGoods)
                .map(goods -> {
                    if (Goods.isEmpty(goods)) {
                        throw new GoodsEmptyDataException();
                    }
                    goods.setId(0);
                    goods.setShopId(shop.getId());
                    return goods;
                })
                .flatMap(goodsWarehouse::add)
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
