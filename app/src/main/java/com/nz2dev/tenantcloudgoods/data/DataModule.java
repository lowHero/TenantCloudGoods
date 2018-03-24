package com.nz2dev.tenantcloudgoods.data;

import com.nz2dev.tenantcloudgoods.domain.preferences.AccountPreferences;
import com.nz2dev.tenantcloudgoods.domain.repositories.GoodsWarehouse;
import com.nz2dev.tenantcloudgoods.domain.repositories.ShopRepository;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    AccountPreferences provideAccountPreferences() {
        throw new RuntimeException("Not implemented!");
    }

    @Provides
    @Singleton
    GoodsWarehouse provideGoodsWarehouse() {
        throw new RuntimeException("Not implemented!");
    }

    @Provides
    @Singleton
    ShopRepository provideShopRepository() {
        throw new RuntimeException("Not implemented!");
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository() {
        throw new RuntimeException("Not implemented!");
    }

}
