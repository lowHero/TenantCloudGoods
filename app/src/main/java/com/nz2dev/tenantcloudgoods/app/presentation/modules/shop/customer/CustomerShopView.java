package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer;

import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;

/**
 * Created by nz2Dev on 25.03.2018
 */
interface CustomerShopView {

    void showGoods(Goods goods);
    void showScanningCanceled();
    void showGoodsNotFound(int goodsId);
    void showBarcodeUnsupported();

    void navigateScanning();
    void navigateCheckout(Check check);

}
