package com.nz2dev.tenantcloudgoods.app.presentation.modules.history;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;

import dagger.Subcomponent;

/**
 * Created by nz2Dev on 27.03.2018
 */
@PerFragment
@Subcomponent
public interface PaymentHistoryComponent {
    void inject(PaymentHistoryFragment paymentHistoryFragment);
}
