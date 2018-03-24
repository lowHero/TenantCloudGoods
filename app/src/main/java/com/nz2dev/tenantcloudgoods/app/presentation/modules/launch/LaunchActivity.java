package com.nz2dev.tenantcloudgoods.app.presentation.modules.launch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.Navigator;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.domain.exceptions.ExternalIdNotSetException;
import com.nz2dev.tenantcloudgoods.domain.exceptions.UserNotRegisteredException;
import com.nz2dev.tenantcloudgoods.domain.interactors.users.GetCurrentUserUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import javax.inject.Inject;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class LaunchActivity extends AppCompatActivity {

    @Inject GetCurrentUserUseCase getCurrentUserUseCase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Dependencies.fromApplication(this).inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            User user = getCurrentUserUseCase.invoke();
            Navigator.navigateHomeFrom(this, user);
        } catch (ExternalIdNotSetException e) {
            Navigator.navigateSignInFrom(this);
        } catch (UserNotRegisteredException e) {
            Navigator.navigateSignInFrom(this);
        }
    }

}
