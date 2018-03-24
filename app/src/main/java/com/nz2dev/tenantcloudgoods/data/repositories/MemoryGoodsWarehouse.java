package com.nz2dev.tenantcloudgoods.data.repositories;

import android.util.SparseArray;

import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.repositories.GoodsWarehouse;

import javax.inject.Inject;
import javax.inject.Singleton;

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
    public void addGoods(Goods goods) {
        goodsMap.put(goods.getId(), goods);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsMap.put(goods.getId(), goods);
    }

    @Override
    public Goods getGoods(int id) {
        return goodsMap.get(id);
    }

}
