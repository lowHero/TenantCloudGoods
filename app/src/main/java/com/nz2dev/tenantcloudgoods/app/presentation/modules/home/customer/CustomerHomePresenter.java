package com.nz2dev.tenantcloudgoods.app.presentation.modules.home.customer;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.BasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.interactors.shops.GetAllShopsUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by nz2Dev on 24.03.2018
 */
@PerFragment
class CustomerHomePresenter extends DisposableBasePresenter<CustomerHomeView> {

    private final GetAllShopsUseCase getAllShopsUseCase;

    private User pendingUser;

    @Inject
    CustomerHomePresenter(GetAllShopsUseCase getAllShopsUseCase) {
        this.getAllShopsUseCase = getAllShopsUseCase;
    }

    void prepareCustomer(User user) {
        pendingUser = user;

        manage("Getting", getAllShopsUseCase
                .executor()
                .subscribe(getView()::showAllShops));
    }

    User getPendingCustomer() {
        return pendingUser;
    }

    void shopClick(Shop shop) {
        getView().navigateShopForCustomer(shop, pendingUser);
    }

}
