package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.exceptions.ExternalIdNotSetException;
import com.nz2dev.tenantcloudgoods.domain.exceptions.UserNotRegisteredException;
import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.preferences.AccountPreferences;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class GetCurrentUserUseCase {

    private final SchedulersManager schedulers;

    private final UserRepository userRepository;
    private final AccountPreferences accountPreferences;

    @Inject
    public GetCurrentUserUseCase(SchedulersManager schedulers, UserRepository userRepository, AccountPreferences accountPreferences) {
        this.schedulers = schedulers;
        this.userRepository = userRepository;
        this.accountPreferences = accountPreferences;
    }

    public Single<User> executor() {
        return Single.just(accountPreferences.getExternalId())
                .map(id -> {
                    if (AccountPreferences.EMPTY_EXTERNAL_ID.equals(id)) {
                        throw new ExternalIdNotSetException();
                    }
                    return id;
                })
                .flatMap(userRepository::getUser)
                .map(user -> {
                    if (User.isEmptyExternalIdHolder(user)) {
                        throw new UserNotRegisteredException(user.getExternalId());
                    }
                    return user;
                })
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}