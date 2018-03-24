package com.nz2dev.tenantcloudgoods.app.presentation.modules.launch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nz2dev.tenantcloudgoods.app.presentation.modules.Navigator;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import javax.inject.Inject;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class LaunchActivity extends AppCompatActivity implements LaunchView {

    @Inject LaunchPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dependencies.fromApplication(this).inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void navigateHome(User user) {
        Navigator.navigateHomeFrom(this, user);
    }

    @Override
    public void navigateSignIn() {
        Navigator.navigateSignInFrom(this);
    }
}
