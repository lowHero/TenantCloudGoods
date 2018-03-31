package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.admin;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.interactors.goods.CreateGoodsByScannedDataUseCase;
import com.nz2dev.tenantcloudgoods.domain.interactors.goods.LoadAllGoodsUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;

import javax.inject.Inject;

/**
 * Created by nz2Dev on 27.03.2018
 */
@PerFragment
class AdminShopPresenter extends DisposableBasePresenter<AdminShopView> {

    private final LoadAllGoodsUseCase loadAllGoodsUseCase;
    private final CreateGoodsByScannedDataUseCase createGoodsByScannedDataUseCase;

    private Shop pendingShop;

    @Inject
    AdminShopPresenter(LoadAllGoodsUseCase loadAllGoodsUseCase, CreateGoodsByScannedDataUseCase createGoodsByScannedDataUseCase) {
        this.loadAllGoodsUseCase = loadAllGoodsUseCase;
        this.createGoodsByScannedDataUseCase = createGoodsByScannedDataUseCase;
    }

    void prepare(Shop shop) {
        pendingShop = shop;

        manage("Preparing", loadAllGoodsUseCase
                .executor(shop)
                .subscribe(getView()::showGoods));
    }

    void handleScannedResult(String scannedResult) {
        manage("Creating", createGoodsByScannedDataUseCase
                .executor(pendingShop, scannedResult)
                .subscribe(goods -> getView().showNewGoods(goods)));
    }

}