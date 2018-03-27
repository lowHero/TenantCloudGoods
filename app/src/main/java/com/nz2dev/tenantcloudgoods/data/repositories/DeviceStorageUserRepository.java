package com.nz2dev.tenantcloudgoods.data.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nz2dev.tenantcloudgoods.data.api.StorageFilesAPI;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;
import com.nz2dev.tenantcloudgoods.domain.utils.CollectionsUtils;

import java.io.IOException;
import java.util.Collections;
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
public class DeviceStorageUserRepository implements UserRepository {

    private final static String USERS_FILE_NAME = "users.json";

    private final StorageFilesAPI storageFilesAPI;
    private final Gson gson = new Gson();

    private Map<String, User> users = null;

    @Inject
    DeviceStorageUserRepository(StorageFilesAPI storageFilesAPI) {
        this.storageFilesAPI = storageFilesAPI;
    }

    @Override
    public Single<User> addUser(User user) {
        return Single.create(emitter -> {
            users.put(user.getExternalId(), user);
            save();
            emitter.onSuccess(user);
        });
    }

    @Override
    public Single<User> getUser(String externalId) {
        return Single.create(emitter -> {
            preload();
            emitter.onSuccess(users.containsKey(externalId)
                    ? users.get(externalId)
                    : User.createEmptyExternalIdHolder(externalId));
        });
    }

    private void save() throws IOException {
        storageFilesAPI.save(USERS_FILE_NAME, gson.toJson(users.values()));
    }

    private void preload() throws IOException {
        if (users == null) {
            users = new HashMap<>();

            if (!storageFilesAPI.isFileExist(USERS_FILE_NAME)) {
                users.putAll(preloadAdmins());
                save();
            } else {
                users.putAll(preloadFromFile());
            }
        }
    }

    private Map<String, User> preloadAdmins() {
        Map<String, User> admins = new HashMap<>();

        User admin = new User(Integer.MAX_VALUE, "nz2develop@gmail.com", true);
        admins.put(admin.getExternalId(), admin);

        return admins;
    }

    private Map<String, User> preloadFromFile() throws IOException {
        String jsonData = storageFilesAPI.load(USERS_FILE_NAME);
        List<User> users = gson.fromJson(jsonData, new TypeToken<List<User>>() {}.getType());

        if (users == null) {
            return Collections.emptyMap();
        } else {
            return CollectionsUtils.toHashMap(users, User::getExternalId);
        }
    }

}
