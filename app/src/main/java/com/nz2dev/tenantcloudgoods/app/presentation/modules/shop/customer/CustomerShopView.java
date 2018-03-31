package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer;

import android.support.annotation.StringRes;

import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 25.03.2018
 */
interface CustomerShopView {

    void showError(@StringRes int templateStringResId, Object... templateParams);
    void showPossibleCheckPrice(float price);
    void showOrder(Order order);
    void showOrderUpdates(Order order);
    void showOrderDeleted(Order orderToDelete);

    void navigateCheckout(Check check, User user);

}
