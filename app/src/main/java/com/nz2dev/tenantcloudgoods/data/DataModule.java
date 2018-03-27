package com.nz2dev.tenantcloudgoods.data;

import com.google.gson.Gson;
import com.nz2dev.tenantcloudgoods.data.preferences.SharedAccountPreferences;
import com.nz2dev.tenantcloudgoods.data.repositories.DeviceStoragePaymentHistory;
import com.nz2dev.tenantcloudgoods.data.repositories.DeviceStorageUserRepository;
import com.nz2dev.tenantcloudgoods.data.repositories.MemoryGoodsWarehouse;
import com.nz2dev.tenantcloudgoods.data.repositories.MemoryShopRepository;
import com.nz2dev.tenantcloudgoods.data.tools.JsonSerializer;
import com.nz2dev.tenantcloudgoods.domain.preferences.AccountPreferences;
import com.nz2dev.tenantcloudgoods.domain.repositories.GoodsWarehouse;
import com.nz2dev.tenantcloudgoods.domain.repositories.PaymentHistory;
import com.nz2dev.tenantcloudgoods.domain.repositories.ShopRepository;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;
import com.nz2dev.tenantcloudgoods.domain.tools.Serializer;

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
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Serializer provideSerializer(JsonSerializer jsonSerializer) {
        return jsonSerializer;
    }

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
    UserRepository provideUserRepository(DeviceStorageUserRepository deviceStorageUserRepository) {
        return deviceStorageUserRepository;
    }

    @Provides
    @Singleton
    PaymentHistory providePaymentHistory(DeviceStoragePaymentHistory deviceStoragePaymentHistory) {
        return deviceStoragePaymentHistory;
    }

}
