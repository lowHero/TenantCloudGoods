package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.admin;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;

import dagger.Subcomponent;

/**
 * Created by nz2Dev on 28.03.2018
 */
@PerFragment
@Subcomponent
public interface AdminShopComponent {
    void inject(AdminShopFragment adminShopFragment);
}
