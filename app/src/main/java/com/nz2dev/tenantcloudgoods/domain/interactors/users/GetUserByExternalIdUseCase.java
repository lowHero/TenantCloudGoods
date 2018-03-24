package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class GetUserByExternalIdUseCase {

    private UserRepository userRepository;

    @Inject
    public GetUserByExternalIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User invoke(String externalId) {
        return userRepository.getUser(externalId);
    }

}
