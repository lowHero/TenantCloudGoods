package com.nz2dev.tenantcloudgoods.app.presentation.modules.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signin.SignInFragment;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signup.SignUpFragment;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class AuthorizationActivity extends AppCompatActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, AuthorizationActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_activity_content, SignInFragment.newInstance())
                .commit();
    }

}
