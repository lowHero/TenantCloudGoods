package com.nz2dev.tenantcloudgoods.domain.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.User;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
public interface UserRepository {

    Single<User> addUser(User user);
    Single<User> getUser(String externalId);

}
