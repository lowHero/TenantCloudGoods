package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.exceptions.UserIsAlreadyRegistered;
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
public class RegisterUserUseCase {

    private final SchedulersManager schedulers;

    private final UserRepository userRepository;

    @Inject
    public RegisterUserUseCase(SchedulersManager schedulers, UserRepository userRepository) {
        this.schedulers = schedulers;
        this.userRepository = userRepository;
    }

    public Single<User> executor(String externalId, boolean admin) {
        return userRepository
                .getUser(externalId)
                .map(user -> {
                    if (!User.isEmptyExternalIdHolder(user)) {
                        throw new UserIsAlreadyRegistered(user.getExternalId());
                    }
                    return User.create(externalId, admin);
                })
                .flatMap(userRepository::addUser)
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
