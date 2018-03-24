package com.nz2dev.tenantcloudgoods.app.presentation.modules.home.admin;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.BasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.interactors.shops.CreateShopUseCase;
import com.nz2dev.tenantcloudgoods.domain.interactors.shops.GetAllShopsUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by nz2Dev on 24.03.2018
 */
@PerFragment
class AdminHomePresenter extends BasePresenter<AdminHomeView> {

    private final GetAllShopsUseCase getAllShopsUseCase;
    private final CreateShopUseCase createShopUseCase;

    private User pendingUser;

    @Inject
    AdminHomePresenter(GetAllShopsUseCase getAllShopsUseCase, CreateShopUseCase createShopUseCase) {
        this.getAllShopsUseCase = getAllShopsUseCase;
        this.createShopUseCase = createShopUseCase;
    }

    void prepareAdmin(User user) {
        pendingUser = user;

        List<Shop> shops = getAllShopsUseCase.invoke();
        getView().showAllShops(shops);
    }

    void addShopClick(Shop shop) {
        createShopUseCase.invoke(shop);
        getView().showShopAdded(shop);
    }

    void selectShopClick(Shop shop) {
        getView().navigateShopForAdmin(shop, pendingUser);
    }

}
