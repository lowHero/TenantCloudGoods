package com.nz2dev.tenantcloudgoods.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;
import com.nz2dev.tenantcloudgoods.data.api.room.TenantCloudGoodsDatabase;
import com.nz2dev.tenantcloudgoods.data.preferences.SharedAccountPreferences;
import com.nz2dev.tenantcloudgoods.data.repositories.RoomCheckRepository;
import com.nz2dev.tenantcloudgoods.data.repositories.RoomGoodsWarehouse;
import com.nz2dev.tenantcloudgoods.data.repositories.RoomShopRepository;
import com.nz2dev.tenantcloudgoods.data.repositories.RoomUserRepository;
import com.nz2dev.tenantcloudgoods.data.tools.JsonSerializer;
import com.nz2dev.tenantcloudgoods.domain.preferences.AccountPreferences;
import com.nz2dev.tenantcloudgoods.domain.repositories.GoodsWarehouse;
import com.nz2dev.tenantcloudgoods.domain.repositories.CheckRepository;
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
    TenantCloudGoodsDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, TenantCloudGoodsDatabase.class, "TenantCloudGoodsDatabase")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    GoodsWarehouse provideGoodsWarehouse(RoomGoodsWarehouse roomGoodsWarehouse) {
        return roomGoodsWarehouse;
    }

    @Provides
    @Singleton
    ShopRepository provideShopRepository(RoomShopRepository roomShopRepository) {
        return roomShopRepository;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(RoomUserRepository roomUserRepository) {
        return roomUserRepository;
    }

    @Provides
    @Singleton
    CheckRepository providePaymentHistory(RoomCheckRepository roomCheckRepository) {
        return roomCheckRepository;
    }

}
