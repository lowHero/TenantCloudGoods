package com.nz2dev.tenantcloudgoods.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.nz2dev.tenantcloudgoods.app.execution.AndroidSchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Module
class AppModule {

    private Application application;

    AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    SchedulersManager provideSchedulersManager(AndroidSchedulersManager androidSchedulersManager) {
        return androidSchedulersManager;
    }

    @SuppressLint("RestrictedApi")
    @Provides
    @Singleton
    GoogleSignInClient provideGoogleSignInClient() {
        return GoogleSignIn.getClient(application,
                new GoogleSignInOptions.Builder(DEFAULT_SIGN_IN)
                        .requestId()
                        .requestProfile()
                        .requestEmail()
                        .build());
    }

}
