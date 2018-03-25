package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer;

import com.google.gson.stream.MalformedJsonException;
import com.google.zxing.integration.android.IntentResult;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.exceptions.GoodsNotFoundException;
import com.nz2dev.tenantcloudgoods.domain.interactors.goods.GetGoodsByScannedResultUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.nz2dev.tenantcloudgoods.app.utils.ThrowableUtils.handleUncaught;

/**
 * Created by nz2Dev on 25.03.2018
 */
@PerFragment
class CustomerShopPresenter extends DisposableBasePresenter<CustomerShopView> {

    private final GetGoodsByScannedResultUseCase getGoodsByScannedResultUseCase;

    private List<Goods> basket = new ArrayList<>();

    @Inject
    CustomerShopPresenter(GetGoodsByScannedResultUseCase getGoodsByScannedResultUseCase) {
        this.getGoodsByScannedResultUseCase = getGoodsByScannedResultUseCase;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();

    }

    void scanClick() {
        // may perform some checks or whatever.
        getView().navigateScanning();
    }

    void handleScanningResult(IntentResult intentResult) {
        if (intentResult.getContents() == null) {
            getView().showScanningCanceled();
        }

        manage("Scanning", getGoodsByScannedResultUseCase
                .executor(intentResult.getContents())
                .doOnSuccess(basket::add)
                .subscribe(getView()::showGoods, throwable -> {
                    if (throwable instanceof GoodsNotFoundException) {
                        GoodsNotFoundException e = (GoodsNotFoundException) throwable;
                        getView().showGoodsNotFound(e.getGoodsId());
                    } else if (throwable instanceof MalformedJsonException) {
                        getView().showBarcodeUnsupported();
                    } else {
                        handleUncaught(throwable);
                    }
                }));
    }

    void checkoutClick() {
        throw new RuntimeException("Not implemented!");
    }

}