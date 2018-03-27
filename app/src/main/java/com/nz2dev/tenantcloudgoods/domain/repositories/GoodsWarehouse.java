package com.nz2dev.tenantcloudgoods.domain.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.Goods;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
public interface GoodsWarehouse {

    Single<Goods> add(Goods goods);
    Single<Boolean> updateGoods(Goods goods);
    Single<Goods> getGoods(int id);
    Single<List<Goods>> getAllGoods();

}
