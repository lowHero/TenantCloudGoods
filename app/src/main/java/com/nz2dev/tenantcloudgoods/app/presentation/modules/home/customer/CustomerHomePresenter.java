package com.nz2dev.tenantcloudgoods.app.presentation.modules.home.customer;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.BasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.interactors.shops.GetAllShopsUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by nz2Dev on 24.03.2018
 */
@PerFragment
class CustomerHomePresenter extends BasePresenter<CustomerHomeView> {

    private final GetAllShopsUseCase getAllShopsUseCase;

    private User pendingUser;

    @Inject
    CustomerHomePresenter(GetAllShopsUseCase getAllShopsUseCase) {
        this.getAllShopsUseCase = getAllShopsUseCase;
    }

    void prepareCustomer(User user) {
        pendingUser = user;

        List<Shop> shops = getAllShopsUseCase.invoke();
        getView().showAllShops(shops);
    }

    void shopClick(Shop shop) {
        getView().navigateShopForCustomer(shop, pendingUser);
    }

}
