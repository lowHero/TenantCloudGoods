package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.admin;

import com.nz2dev.tenantcloudgoods.domain.models.Goods;

import java.util.List;

/**
 * Created by nz2Dev on 27.03.2018
 */
interface AdminShopView {

    void showGoods(List<Goods> goods);
    void showNewGoods(Goods goods);

}
