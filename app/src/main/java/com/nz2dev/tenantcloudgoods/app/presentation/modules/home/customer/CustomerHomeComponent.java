package com.nz2dev.tenantcloudgoods.app.presentation.modules.home.customer;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;

import dagger.Subcomponent;

/**
 * Created by nz2Dev on 24.03.2018
 */
@PerFragment
@Subcomponent
public interface CustomerHomeComponent {
    void inject(CustomerHomeFragment customerHomeFragment);
}
