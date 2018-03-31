package com.nz2dev.tenantcloudgoods.app.presentation.modules.history;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.interactors.orders.LoadPaymentHistoryUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import javax.inject.Inject;

/**
 * Created by nz2Dev on 27.03.2018
 */
@PerFragment
class PaymentHistoryPresenter extends DisposableBasePresenter<PaymentHistoryView> {

    private final LoadPaymentHistoryUseCase loadPaymentHistoryUseCase;

    @Inject
    PaymentHistoryPresenter(LoadPaymentHistoryUseCase loadPaymentHistoryUseCase) {
        this.loadPaymentHistoryUseCase = loadPaymentHistoryUseCase;
    }

    void prepare(User user) {
        super.onViewReady();
        manage(loadPaymentHistoryUseCase
                .executor(user)
                .subscribe(getView()::showHistory));
    }

}