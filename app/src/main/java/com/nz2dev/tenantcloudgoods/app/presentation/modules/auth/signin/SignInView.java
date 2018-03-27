package com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signin;

import android.support.annotation.StringRes;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 24.03.2018
 */
interface SignInView {

    void showError(@StringRes int templateStringResId, Object... templateParams);
    void showAccountNotRegistered();
    void showAccountSelected(boolean selected);
    void showAccountAndUserData(GoogleSignInAccount googleAccount, User user);

    void navigateSignInGoogleAccount();
    void navigateSignOutGoogleAccount();
    void navigateHome(User user);

}
