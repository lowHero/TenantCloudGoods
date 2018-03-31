package com.nz2dev.tenantcloudgoods.app.presentation.modules.history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 27.03.2018
 */
public class PaymentHistoryActivity extends AppCompatActivity {

    public static Intent getCallingIntent(Context context, User user) {
        return new Intent(context, PaymentHistoryActivity.class)
                .putExtra(user.getClass().getName(), user);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        User user = (User) getIntent().getSerializableExtra(User.class.getName());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_activity_content, PaymentHistoryFragment.newInstance(user))
                .commit();
    }

}