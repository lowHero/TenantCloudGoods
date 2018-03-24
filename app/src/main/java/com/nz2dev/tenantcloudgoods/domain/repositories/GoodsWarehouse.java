package com.nz2dev.tenantcloudgoods.domain.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.Goods;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
public interface GoodsWarehouse {

    Single<Boolean> addGoods(Goods goods);
    Single<Boolean> updateGoods(Goods goods);
    Single<Goods> getGoods(int id);

}
