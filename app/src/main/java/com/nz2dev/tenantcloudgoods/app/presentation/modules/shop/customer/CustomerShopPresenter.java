package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer;

import com.google.gson.JsonSyntaxException;
import com.google.zxing.integration.android.IntentResult;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.exceptions.GoodsNotFoundException;
import com.nz2dev.tenantcloudgoods.domain.interactors.orders.CreateCheckUserCase;
import com.nz2dev.tenantcloudgoods.domain.interactors.orders.CreateOrderByScannedResultUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.Order;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.nz2dev.tenantcloudgoods.app.utils.ThrowableUtils.handleUncaught;

/**
 * Created by nz2Dev on 25.03.2018
 */
@PerFragment
class CustomerShopPresenter extends DisposableBasePresenter<CustomerShopView> {

    private final CreateOrderByScannedResultUseCase createOrderByScannedResultUseCase;
    private final CreateCheckUserCase createCheckUserCase;

    private List<Order> basket = new ArrayList<>();

    @Inject
    CustomerShopPresenter(CreateOrderByScannedResultUseCase createOrderByScannedResultUseCase, CreateCheckUserCase createCheckUserCase) {
        this.createOrderByScannedResultUseCase = createOrderByScannedResultUseCase;
        this.createCheckUserCase = createCheckUserCase;
    }

    void scanClick() {
        // may perform some checks or whatever.
        getView().navigateScanning();
    }

    void handleScanningResult(IntentResult intentResult) {
        if (intentResult.getContents() == null) {
            getView().showScanningCanceled();
            return;
        }

        manage("Scanning", createOrderByScannedResultUseCase
                .executor(intentResult.getContents())
                .doOnSuccess(basket::add)
                .subscribe(getView()::showOrder, throwable -> {
                    if (throwable instanceof GoodsNotFoundException) {
                        GoodsNotFoundException e = (GoodsNotFoundException) throwable;
                        getView().showGoodsNotFound(e.getGoodsId());
                    } else if (throwable instanceof JsonSyntaxException) {
                        getView().showInvalidScanData();
                    } else {
                        handleUncaught(throwable);
                    }
                }));
    }

    void checkoutClick() {
        manage("Creating", createCheckUserCase
                .executor(basket)
                .subscribe(getView()::navigateCheckout));
    }

}