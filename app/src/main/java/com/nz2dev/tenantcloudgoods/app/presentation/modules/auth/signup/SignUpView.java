package com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signup;

import android.support.annotation.StringRes;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 24.03.2018
 */
interface SignUpView {

    void showError(@StringRes int templateStringResId, Object... templateParams);
    void showAccountSelected(boolean selected);
    void showAdminSelected(boolean selected);
    void showAccount(GoogleSignInAccount googleAccount);

    void navigateSignInGoogleAccount();
    void navigateSignOutGoogleAccount();
    void navigateSignIn();

}
