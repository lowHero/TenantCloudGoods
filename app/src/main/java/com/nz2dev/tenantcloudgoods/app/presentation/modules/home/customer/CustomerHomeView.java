package com.nz2dev.tenantcloudgoods.app.presentation.modules.home.customer;

import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import java.util.List;

/**
 * Created by nz2Dev on 24.03.2018
 */
interface CustomerHomeView {

    void showAllShops(List<Shop> shops);
    void navigateShopForCustomer(Shop shop, User customer);

}
