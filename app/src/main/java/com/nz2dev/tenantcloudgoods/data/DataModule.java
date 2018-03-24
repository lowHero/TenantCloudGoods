package com.nz2dev.tenantcloudgoods.data;

import com.nz2dev.tenantcloudgoods.data.preferences.SharedAccountPreferences;
import com.nz2dev.tenantcloudgoods.data.repositories.MemoryGoodsWarehouse;
import com.nz2dev.tenantcloudgoods.data.repositories.MemoryShopRepository;
import com.nz2dev.tenantcloudgoods.data.repositories.MemoryUserRepository;
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
    AccountPreferences provideAccountPreferences(SharedAccountPreferences sharedAccountPreferences) {
        return sharedAccountPreferences;
    }

    @Provides
    @Singleton
    GoodsWarehouse provideGoodsWarehouse(MemoryGoodsWarehouse memoryGoodsWarehouse) {
        return memoryGoodsWarehouse;
    }

    @Provides
    @Singleton
    ShopRepository provideShopRepository(MemoryShopRepository memoryShopRepository) {
        return memoryShopRepository;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(MemoryUserRepository memoryUserRepository) {
        return memoryUserRepository;
    }

}
