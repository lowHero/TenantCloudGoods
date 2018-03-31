package com.nz2dev.tenantcloudgoods.app.presentation.modules;

import android.app.Activity;

import com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.AuthorizationActivity;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.history.PaymentHistoryActivity;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.home.HomeActivity;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.ShopActivity;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 24.03.2018
 */
public final class Navigator {

    public static void navigateAuthorizationFrom(Activity activity) {
        activity.startActivity(AuthorizationActivity.getCallingIntent(activity));
    }

    public static void navigateHomeFrom(Activity activity, User user) {
        activity.startActivity(HomeActivity.getCallingIntent(activity, user));
    }

    public static void navigatePaymentHistoryFrom(Activity activity, User user) {
        activity.startActivity(PaymentHistoryActivity.getCallingIntent(activity, user));
    }

    public static void navigateShopFrom(Activity activity, Shop shop, User user) {
        activity.startActivity(ShopActivity.getCallingIntent(activity, shop, user));
    }

}
