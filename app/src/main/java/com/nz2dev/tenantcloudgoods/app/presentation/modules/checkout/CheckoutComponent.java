package com.nz2dev.tenantcloudgoods.app.presentation.modules.checkout;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;

import dagger.Subcomponent;

/**
 * Created by nz2Dev on 26.03.2018
 */
@PerFragment
@Subcomponent
public interface CheckoutComponent {
    void inject(CheckoutFragment checkoutFragment);
}
