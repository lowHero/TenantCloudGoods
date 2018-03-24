package com.nz2dev.tenantcloudgoods.data.repositories;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nz2dev.tenantcloudgoods.data.api.StorageFilesAPI;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class MemoryUserRepository implements UserRepository {

    private final static String USERS_FILE_NAME = "users.json";

    private final StorageFilesAPI storageFilesAPI;
    private final Gson gson = new Gson();

    private Map<String, User> memoryUsers = null;

    @Inject
    MemoryUserRepository(StorageFilesAPI storageFilesAPI) {
        this.storageFilesAPI = storageFilesAPI;
    }

    @Override
    public Single<User> addUser(User user) {
        return Single.create(emitter -> {
            memoryUsers.put(user.getExternalId(), user);
            save();
            emitter.onSuccess(user);
        });
    }

    @Override
    public Single<User> getUser(String externalId) {
        return Single.create(emitter -> {
            preload();
            emitter.onSuccess(memoryUsers.containsKey(externalId)
                    ? memoryUsers.get(externalId)
                    : User.EMPTY);
        });
    }

    private void save() throws IOException {
        storageFilesAPI.save(USERS_FILE_NAME, gson.toJson(memoryUsers.values()));
    }

    private void preload() throws IOException {
        if (memoryUsers == null) {
            String jsonData = storageFilesAPI.load(USERS_FILE_NAME);

            List<User> users = gson.fromJson(jsonData, new TypeToken<List<User>>() {}.getType());
            if (users == null) {
                memoryUsers = new HashMap<>();
                return;
            }

            memoryUsers = new HashMap<>(users.size());
            for (User user : users) {
                memoryUsers.put(user.getExternalId(), user);
            }
        }
    }

}
