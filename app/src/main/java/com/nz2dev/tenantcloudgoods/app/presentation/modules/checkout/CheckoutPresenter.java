package com.nz2dev.tenantcloudgoods.app.presentation.modules.checkout;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.exceptions.CheckDataGenerationException;
import com.nz2dev.tenantcloudgoods.domain.interactors.orders.GenerateCheckDataUseCase;
import com.nz2dev.tenantcloudgoods.domain.interactors.orders.SaveCheckToHistoryUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.Check;

import javax.inject.Inject;

import static com.nz2dev.tenantcloudgoods.app.utils.ThrowableUtils.handleUncaught;

/**
 * Created by nz2Dev on 26.03.2018
 */
@PerFragment
class CheckoutPresenter extends DisposableBasePresenter<CheckoutView> {

    private final GenerateCheckDataUseCase generateCheckDataUseCase;
    private final SaveCheckToHistoryUseCase saveCheckToHistoryUseCase;

    private Check pendingCheck;

    @Inject
    CheckoutPresenter(GenerateCheckDataUseCase generateCheckDataUseCase, SaveCheckToHistoryUseCase saveCheckToHistoryUseCase) {
        this.generateCheckDataUseCase = generateCheckDataUseCase;
        this.saveCheckToHistoryUseCase = saveCheckToHistoryUseCase;
    }

    void prepare(Check check) {
        pendingCheck = check;
        getView().showCheckAmount(check.getAmount());
        getView().showCheckOrders(check.getOrders());
    }

    void confirmCheckout() {
        manage("Confirming", saveCheckToHistoryUseCase
                .executor(pendingCheck)
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