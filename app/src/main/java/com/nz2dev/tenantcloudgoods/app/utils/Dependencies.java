package com.nz2dev.tenantcloudgoods.app.utils;

import android.content.Context;

import com.nz2dev.tenantcloudgoods.app.AppComponent;
import com.nz2dev.tenantcloudgoods.app.TenantCloudGoodsApp;

/**
 * Created by nz2Dev on 24.03.2018
 */
public final class Dependencies {

    public static AppComponent fromApplication(Context context) {
        TenantCloudGoodsApp app = (TenantCloudGoodsApp) context.getApplicationContext();
        return app.getDependencies();
    }

}
