package com.nz2dev.tenantcloudgoods.app.presentation.modules;

import android.app.Activity;

import com.nz2dev.tenantcloudgoods.app.presentation.modules.home.HomeActivity;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.login.GoogleSignInActivity;
import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 24.03.2018
 */
public final class Navigator {

    public static void navigateSignInFrom(Activity activity) {
        activity.startActivity(GoogleSignInActivity.getCallingIntent(activity));
    }

    public static void navigateHomeFrom(Activity activity, User user) {
        activity.startActivity(HomeActivity.getCallingIntent(activity, user));
    }

}
