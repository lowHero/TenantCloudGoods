package com.nz2dev.tenantcloudgoods.domain.interactors.orders;

import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.Payment;
import com.nz2dev.tenantcloudgoods.domain.repositories.PaymentHistory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 27.03.2018
 */
@Singleton
public class LoadAllPaymentHistoryUseCase {

    private final SchedulersManager schedulers;

    private final PaymentHistory paymentHistory;

    @Inject
    public LoadAllPaymentHistoryUseCase(SchedulersManager schedulers, PaymentHistory paymentHistory) {
        this.schedulers = schedulers;
        this.paymentHistory = paymentHistory;
    }

    public Single<List<Payment>> executor() {
        return paymentHistory.getAll()
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
