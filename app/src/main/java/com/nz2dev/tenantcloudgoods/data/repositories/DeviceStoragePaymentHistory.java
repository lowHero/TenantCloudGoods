package com.nz2dev.tenantcloudgoods.data.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nz2dev.tenantcloudgoods.data.api.StorageFilesAPI;
import com.nz2dev.tenantcloudgoods.domain.models.Payment;
import com.nz2dev.tenantcloudgoods.domain.repositories.PaymentHistory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 27.03.2018
 */
@Singleton
public class DeviceStoragePaymentHistory implements PaymentHistory {

    private final static String PAYMENT_HISTORY_FILENAME = "payment_history.json";

    private final StorageFilesAPI storageFilesAPI;
    private final Gson gson;

    @Inject
    public DeviceStoragePaymentHistory(StorageFilesAPI storageFilesAPI, Gson gson) {
        this.storageFilesAPI = storageFilesAPI;
        this.gson = gson;
    }

    @Override
    public Single<Boolean> add(Payment history) {
        return Single.create(emitter -> {
            List<Payment> newHistory = new ArrayList<>(preloadFromStorage());
            newHistory.add(history);
            saveIntoStorage(newHistory);
            emitter.onSuccess(true);
        });
    }

    @Override
    public Single<List<Payment>> getAll() {
        return Single.create(emitter -> emitter.onSuccess(preloadFromStorage()));
    }

    private List<Payment> preloadFromStorage() throws IOException {
        String data = storageFilesAPI.load(PAYMENT_HISTORY_FILENAME);
        if (data == null || data.length() == 0) {
            return Collections.emptyList();
        }
        return gson.fromJson(data, new TypeToken<List<Payment>>() {}.getType());
    }

    private void saveIntoStorage(List<Payment> payments) throws IOException {
        storageFilesAPI.save(PAYMENT_HISTORY_FILENAME, gson.toJson(payments));
    }

}
