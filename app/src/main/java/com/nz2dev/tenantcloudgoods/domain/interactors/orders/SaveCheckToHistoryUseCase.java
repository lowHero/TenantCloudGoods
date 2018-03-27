package com.nz2dev.tenantcloudgoods.domain.interactors.orders;

import com.nz2dev.tenantcloudgoods.domain.exceptions.SavePaymentToHistoryFailException;
import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Payment;
import com.nz2dev.tenantcloudgoods.domain.repositories.PaymentHistory;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 27.03.2018
 */
@Singleton
public class SaveCheckToHistoryUseCase {

    private final SchedulersManager schedulers;

    private final PaymentHistory paymentHistory;

    @Inject
    public SaveCheckToHistoryUseCase(SchedulersManager schedulers, PaymentHistory paymentHistory) {
        this.schedulers = schedulers;
        this.paymentHistory = paymentHistory;
    }

    public Single<Check> executor(Check check) {
        return Single.just(Payment.createSinceNow(check))
                .flatMap(paymentHistory::add)
                .map(result -> {
                    if (!result) {
                        throw new SavePaymentToHistoryFailException(check);
                    }
                    return check;
                })
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
