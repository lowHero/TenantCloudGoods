package com.nz2dev.tenantcloudgoods.app.presentation.modules.checkout;

import android.support.annotation.StringRes;

import com.nz2dev.tenantcloudgoods.domain.models.Order;

import java.util.List;

/**
 * Created by nz2Dev on 26.03.2018
 */
interface CheckoutView {

    void showError(@StringRes int templateStringResId, Object... templateParams);
    void showCheckAmount(float amount);
    void showCheckOrders(List<Order> orders);
    void showScan(String checkData);

}
