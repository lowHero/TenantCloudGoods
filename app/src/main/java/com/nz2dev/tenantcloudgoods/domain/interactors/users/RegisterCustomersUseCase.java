package com.nz2dev.tenantcloudgoods.domain.interactors.users;

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
public class RegisterCustomersUseCase {

    private final SchedulersManager schedulers;

    private final UserRepository userRepository;

    @Inject
    public RegisterCustomersUseCase(SchedulersManager schedulers, UserRepository userRepository) {
        this.schedulers = schedulers;
        this.userRepository = userRepository;
    }

    public Single<User> executor(String externalId) {
        return Single.just(User.createCustomer(externalId))
                // ...
                // TODO check if user already registered and throw exceptions.
                // ...
                .flatMap(userRepository::addUser)
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
