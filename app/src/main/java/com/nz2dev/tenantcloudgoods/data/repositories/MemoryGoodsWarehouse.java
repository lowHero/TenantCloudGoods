package com.nz2dev.tenantcloudgoods.data.repositories;

import android.util.SparseArray;

import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.repositories.GoodsWarehouse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class MemoryGoodsWarehouse implements GoodsWarehouse {

    private SparseArray<Goods> goodsMap = new SparseArray<>();

    @Inject
    public MemoryGoodsWarehouse() {
    }

    @Override
    public Single<Boolean> addGoods(Goods goods) {
        goodsMap.put(goods.getId(), goods);
        return Single.just(true);
    }

    @Override
    public Single<Boolean> updateGoods(Goods goods) {
        goodsMap.put(goods.getId(), goods);
        return Single.just(true);
    }

    @Override
    public Single<Goods> getGoods(int id) {
        return Single.just(goodsMap.get(id));
    }

}
