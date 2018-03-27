package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer;

import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.models.Order;

/**
 * Created by nz2Dev on 25.03.2018
 */
interface CustomerShopView {

    void showPossibleCheckPrice(float price);
    void showOrder(Order order);
    void showOrderUpdates(Order order);
    void showOrderDeleted(Order orderToDelete);
    void showOrderAlreadyExist();
    void showGoodsNotFound(int goodsId);
    void showInvalidScanData();

    void navigateCheckout(Check check);

}
