package com.nz2dev.tenantcloudgoods.data.repositories;

import android.annotation.SuppressLint;
import android.util.SparseArray;

import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.repositories.GoodsWarehouse;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
@SuppressLint("UseSparseArrays")
public class MemoryGoodsWarehouse implements GoodsWarehouse {

    private Map<Integer, Goods> goodsMap;

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
        return Single.create(emitter -> {
            preload();
            Goods goods = goodsMap.get(id);
            emitter.onSuccess(goods != null ? goods : Goods.emptyIdHolder(id));
        });
    }

    private void preload() {
        if (goodsMap == null) {
            goodsMap = new HashMap<>();
            goodsMap.putAll(preloadPredefinedGoods());
        }
    }

    private Map<Integer, Goods> preloadPredefinedGoods() {
        Map<Integer, Goods> predefinedGoods = new HashMap<>();

        Goods cocaCola = new Goods(1, "Coca-Cola", null, 100);
        predefinedGoods.put(cocaCola.getId(), cocaCola);

        Goods nuts = new Goods(2, "Nuts", null, 100);
        predefinedGoods.put(nuts.getId(), nuts);

        return predefinedGoods;
    }

}
