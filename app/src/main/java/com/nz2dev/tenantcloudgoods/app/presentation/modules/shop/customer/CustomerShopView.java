package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer;

import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.models.Order;

/**
 * Created by nz2Dev on 25.03.2018
 */
interface CustomerShopView {

    void showOrder(Order order);
    void showScanningCanceled();
    void showGoodsNotFound(int goodsId);
    void showInvalidScanData();

    void navigateScanning();
    void navigateCheckout(Check check);

}
