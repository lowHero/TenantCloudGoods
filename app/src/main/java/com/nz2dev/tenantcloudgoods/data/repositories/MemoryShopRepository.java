package com.nz2dev.tenantcloudgoods.data.repositories;

import com.nz2dev.tenantcloudgoods.data.api.StorageFilesAPI;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.repositories.ShopRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class MemoryShopRepository implements ShopRepository {

    private List<Shop> shops;
    private StorageFilesAPI storageFilesAPI;

    @Inject
    public MemoryShopRepository(StorageFilesAPI storageFilesAPI) {
        this.storageFilesAPI = storageFilesAPI;
    }

    @Override
    public Single<Shop> createShop(Shop shop) {
        return Single.create(emitter -> {
            shops.add(shop);
            save();
            emitter.onSuccess(shop);
        });
    }

    @Override
    public Single<List<Shop>> getAllShops() {
        return Single.create(emitter -> {
            preload();
            emitter.onSuccess(new ArrayList<>(shops));
        });
    }

    private void save() {
        // TODO implement.
    }

    private void preload() {
        if (shops == null) {
            shops = new ArrayList<>();
            shops.addAll(preloadPredefinedShops());
        }
    }

    private List<Shop> preloadPredefinedShops() {
        return Arrays.asList(
                new Shop(1, "Shop1"),
                new Shop(2, "Shop2")
        );
    }

}
