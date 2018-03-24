package com.nz2dev.tenantcloudgoods.data.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class MemoryUserRepository implements UserRepository {

    private Map<String, User> memoryUsers = new HashMap<>();

    @Inject
    public MemoryUserRepository() {
    }

    @Override
    public void addUser(User user) {
        memoryUsers.put(user.getExternalId(), user);
    }

    @Override
    public User getUser(String externalId) {
        if (!memoryUsers.containsKey(externalId)) {
            throw new RuntimeException("User with externalId: " + externalId + " not registered");
        }
        return memoryUsers.get(externalId);
    }

}
