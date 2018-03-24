package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.preferences.AccountPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class SignInUserUseCase {

    private final AccountPreferences accountPreferences;

    @Inject
    SignInUserUseCase(AccountPreferences accountPreferences) {
        this.accountPreferences = accountPreferences;
    }

    public Single<User> executor(User user) {
        return Single.create(emitter -> {
            accountPreferences.saveExternalId(user.getExternalId());
            emitter.onSuccess(user);
        });
    }

}
