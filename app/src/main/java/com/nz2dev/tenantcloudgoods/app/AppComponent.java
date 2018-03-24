package com.nz2dev.tenantcloudgoods.app;

import com.nz2dev.tenantcloudgoods.app.presentation.modules.launch.LaunchActivity;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.login.GoogleSignInComponent;
import com.nz2dev.tenantcloudgoods.data.DataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    void inject(LaunchActivity launchActivity);
    GoogleSignInComponent createGoogleSignInComponent();
}
