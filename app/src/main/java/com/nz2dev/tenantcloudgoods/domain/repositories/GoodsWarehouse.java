package com.nz2dev.tenantcloudgoods.domain.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
public interface GoodsWarehouse {

    Single<Goods> add(Goods goods);
    Single<Goods> getGoods(long id);
    Single<List<Goods>> getAllGoods(long shopId);

}
