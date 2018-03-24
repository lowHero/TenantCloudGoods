package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.exceptions.UserNotRegisteredException;
import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class GetUserByExternalIdUseCase {

    private final SchedulersManager schedulers;

    private final UserRepository userRepository;

    @Inject
    public GetUserByExternalIdUseCase(SchedulersManager schedulers, UserRepository userRepository) {
        this.schedulers = schedulers;
        this.userRepository = userRepository;
    }

    public Single<User> executor(String externalId) {
        return userRepository.getUser(externalId)
                .map(user -> {
                    if (user.isEmpty()) {
                        throw new UserNotRegisteredException();
                    }
                    return user;
                })
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
