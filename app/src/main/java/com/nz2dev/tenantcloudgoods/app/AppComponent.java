package com.nz2dev.tenantcloudgoods.app;

import com.nz2dev.tenantcloudgoods.app.presentation.modules.home.admin.AdminHomeComponent;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.home.customer.CustomerHomeComponent;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.launch.LaunchActivity;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.login.GoogleSignInComponent;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer.CustomerShopComponent;
import com.nz2dev.tenantcloudgoods.data.DataModule;
import com.nz2dev.tenantcloudgoods.device.DeviceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
@Component(modules = {AppModule.class, DataModule.class, DeviceModule.class})
public interface AppComponent {

    // TODO consider make component for this module too.
    // TODO consider to make each domain UseCase unscoped
    void inject(LaunchActivity launchActivity);

    GoogleSignInComponent createGoogleSignInComponent();
    CustomerHomeComponent createCustomerHomeComponent();
    AdminHomeComponent createAdminHomeComponent();
    CustomerShopComponent createCustomerShopComponent();

}
