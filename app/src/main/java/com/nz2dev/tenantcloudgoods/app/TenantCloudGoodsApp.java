package com.nz2dev.tenantcloudgoods.app;

import android.app.Application;

import com.squareup.picasso.Picasso;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class TenantCloudGoodsApp extends Application {

    private AppComponent dependencies;

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso.setSingletonInstance(new Picasso.Builder(this).build());
        dependencies = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getDependencies() {
        return dependencies;
    }

}
