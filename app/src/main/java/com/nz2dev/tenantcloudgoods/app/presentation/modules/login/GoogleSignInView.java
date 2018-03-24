package com.nz2dev.tenantcloudgoods.app.presentation.modules.login;

import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 24.03.2018
 */
public interface GoogleSignInView {

    void showApiError(String error);
    void navigateHome(User user);

}
