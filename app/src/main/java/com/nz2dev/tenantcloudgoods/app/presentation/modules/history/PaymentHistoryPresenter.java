package com.nz2dev.tenantcloudgoods.app.presentation.modules.history;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.interactors.orders.LoadAllPaymentHistoryUseCase;

import javax.inject.Inject;

/**
 * Created by nz2Dev on 27.03.2018
 */
@PerFragment
public class PaymentHistoryPresenter extends DisposableBasePresenter<PaymentHistoryView> {

    private final LoadAllPaymentHistoryUseCase loadAllPaymentHistoryUseCase;

    @Inject
    public PaymentHistoryPresenter(LoadAllPaymentHistoryUseCase loadAllPaymentHistoryUseCase) {
        this.loadAllPaymentHistoryUseCase = loadAllPaymentHistoryUseCase;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        manage(loadAllPaymentHistoryUseCase
                .executor()
                .subscribe(getView()::showHistory));
    }

}