package com.nz2dev.tenantcloudgoods.app.presentation.modules.launch;

import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 24.03.2018
 */
interface LaunchView {

    void navigateHome(User user);
    void navigateSignIn();

}
