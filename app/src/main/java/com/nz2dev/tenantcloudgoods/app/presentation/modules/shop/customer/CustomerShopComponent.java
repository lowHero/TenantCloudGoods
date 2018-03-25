package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;

import dagger.Subcomponent;

/**
 * Created by nz2Dev on 25.03.2018
 */
@PerFragment
@Subcomponent
public interface CustomerShopComponent {
    void inject(CustomerShopFragment customerShopFragment);
}
