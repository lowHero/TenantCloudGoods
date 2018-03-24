package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.preferences.AccountPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class SignInUserUseCase {

    private AccountPreferences accountPreferences;

    @Inject
    SignInUserUseCase(AccountPreferences accountPreferences) {
        this.accountPreferences = accountPreferences;
    }

    public void invoke(User user) {
        accountPreferences.saveExternalId(user.getExternalId());
    }

}
