package com.nz2dev.tenantcloudgoods.app.utils;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

/**
 * Created by nz2Dev on 26.03.2018
 */
public class FragmentHelper {

    @Nullable
    public static String getLastBackStackName(FragmentManager fragmentManager) {
        if (fragmentManager.getBackStackEntryCount() == 0) {
            return null;
        }

        int lastBackStackIndex = fragmentManager.getBackStackEntryCount() - 1;
        return fragmentManager.getBackStackEntryAt(lastBackStackIndex).getName();
    }

}
