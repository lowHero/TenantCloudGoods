package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.exceptions.ExternalIdNotSetException;
import com.nz2dev.tenantcloudgoods.domain.exceptions.UserNotRegisteredException;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.preferences.AccountPreferences;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class GetCurrentUserUseCase {

    private UserRepository userRepository;
    private AccountPreferences accountPreferences;

    @Inject
    public GetCurrentUserUseCase(UserRepository userRepository, AccountPreferences accountPreferences) {
        this.userRepository = userRepository;
        this.accountPreferences = accountPreferences;
    }

    public User invoke() {
        String externalId = accountPreferences.getExternalId();
        if (externalId == null) {
            throw new ExternalIdNotSetException();
        }

        User user = userRepository.getUser(externalId);
        if (user == null) {
            throw new UserNotRegisteredException();
        }
        return user;
    }

}