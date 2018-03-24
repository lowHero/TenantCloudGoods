package com.nz2dev.tenantcloudgoods.app.presentation.modules.home.admin;

import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import java.util.List;

/**
 * Created by nz2Dev on 24.03.2018
 */
interface AdminHomeView {

    void showAllShops(List<Shop> shops);
    void showShopAdded(Shop shop);
    void navigateShopForAdmin(Shop shop, User admin);

}
