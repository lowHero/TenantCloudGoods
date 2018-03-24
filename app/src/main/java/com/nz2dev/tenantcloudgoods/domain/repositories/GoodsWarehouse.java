package com.nz2dev.tenantcloudgoods.domain.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.Goods;

/**
 * Created by nz2Dev on 24.03.2018
 */
public interface GoodsWarehouse {

    void addGoods(Goods goods);
    void updateGoods(Goods goods);
    Goods getGoods(int id);

}
