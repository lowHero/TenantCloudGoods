package com.nz2dev.tenantcloudgoods.domain.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 24.03.2018
 */
public interface UserRepository {

    void addUser(User user);
    User getUser(String externalId);

}
