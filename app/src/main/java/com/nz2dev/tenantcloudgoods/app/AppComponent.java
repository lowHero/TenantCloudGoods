package com.nz2dev.tenantcloudgoods.app;

import com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signin.SignInComponent;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signup.SignUpComponent;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.checkout.CheckoutComponent;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.home.admin.AdminHomeComponent;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.home.customer.CustomerHomeComponent;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer.CustomerShopComponent;
import com.nz2dev.tenantcloudgoods.data.DataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    SignInComponent createSignInComponent();
    SignUpComponent createSignUpComponent();
    CustomerHomeComponent createCustomerHomeComponent();
    AdminHomeComponent createAdminHomeComponent();
    CustomerShopComponent createCustomerShopComponent();
    CheckoutComponent createCheckoutComponent();

}
