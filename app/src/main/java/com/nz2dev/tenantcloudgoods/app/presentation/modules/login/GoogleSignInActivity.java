package com.nz2dev.tenantcloudgoods.app.presentation.modules.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nz2dev.tenantcloudgoods.R;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class GoogleSignInActivity extends AppCompatActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, GoogleSignInActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_activity_content, GoogleSignInFragment.newInstance())
                .commit();
    }

}
