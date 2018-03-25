package com.nz2dev.tenantcloudgoods.domain.interactors.goods;

import com.nz2dev.tenantcloudgoods.domain.exceptions.GoodsNotFoundException;
import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.repositories.GoodsWarehouse;
import com.nz2dev.tenantcloudgoods.domain.tools.Serializer;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 25.03.2018
 */
@Singleton
public class GetGoodsByScannedResultUseCase {

    private final SchedulersManager schedulers;

    private final GoodsWarehouse goodsWarehouse;
    private final Serializer serializer;

    @Inject
    public GetGoodsByScannedResultUseCase(SchedulersManager schedulers, GoodsWarehouse goodsWarehouse, Serializer serializer) {
        this.schedulers = schedulers;
        this.goodsWarehouse = goodsWarehouse;
        this.serializer = serializer;
    }

    public Single<Goods> executor(String scannedData) {
        return Single.just(scannedData)
                .map(serializer::deserializeGoodsIdentifier)
                .flatMap(goodsIdentifier -> goodsWarehouse.getGoods(goodsIdentifier.id))
                .map(goods -> {
                    if (Goods.isEmptyIdHolder(goods)) {
                        throw new GoodsNotFoundException(goods.getId());
                    }
                    return goods;
                })
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
