package com.nz2dev.tenantcloudgoods.app.presentation.modules.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.home.admin.AdminHomeFragment;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.home.customer.CustomerHomeFragment;
import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class HomeActivity extends AppCompatActivity {

    private static final String EXTRA_USER = "user";

    public static Intent getCallingIntent(Context context, User user) {
        return new Intent(context, HomeActivity.class)
                .putExtra(EXTRA_USER, user);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        final User user = (User) getIntent().getSerializableExtra(EXTRA_USER);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_activity_content, user.isAdmin()
                        ? AdminHomeFragment.newInstance(user)
                        : CustomerHomeFragment.newInstance(user))
                .commit();
    }

}
