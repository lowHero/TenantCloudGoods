package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.checkout;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.exceptions.CheckDataGenerationException;
import com.nz2dev.tenantcloudgoods.domain.interactors.orders.GenerateCheckDataUseCase;
import com.nz2dev.tenantcloudgoods.domain.interactors.orders.PerformPaymentUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import javax.inject.Inject;

import static com.nz2dev.tenantcloudgoods.app.utils.ThrowableUtils.handleUncaught;

/**
 * Created by nz2Dev on 26.03.2018
 */
@PerFragment
class CheckoutPresenter extends DisposableBasePresenter<CheckoutView> {

    private final GenerateCheckDataUseCase generateCheckDataUseCase;
    private final PerformPaymentUseCase performPaymentUseCase;

    private Check pendingCheck;
    private User pendingUser;

    @Inject
    CheckoutPresenter(GenerateCheckDataUseCase generateCheckDataUseCase, PerformPaymentUseCase performPaymentUseCase) {
        this.generateCheckDataUseCase = generateCheckDataUseCase;
        this.performPaymentUseCase = performPaymentUseCase;
    }

    void prepare(Check check, User user) {
        pendingCheck = check;
        pendingUser = user;
        getView().showCheckAmount(Order.priceOf(check.getOrders()));
        getView().showCheckOrders(check.getOrders());
    }

    void confirmCheckout() {
        manage("Confirming", performPaymentUseCase
                .executor(pendingCheck, pendingUser)
                .flatMap(generateCheckDataUseCase::executor)
                .subscribe(checkData -> getView().showScan(checkData), throwable -> {
                    if (throwable instanceof CheckDataGenerationException) {
                        getView().showError(R.string.error_fail_to_generate_check_data);
                    } else {
                        handleUncaught(throwable);
                    }
                }));
    }

}