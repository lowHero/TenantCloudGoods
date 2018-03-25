package com.nz2dev.tenantcloudgoods.app.presentation.modules.checkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.domain.models.Check;

/**
 * Created by nz2Dev on 26.03.2018
 */
public class CheckoutActivity extends AppCompatActivity {

    public static Intent getCallingIntent(Context context, Check check) {
        return new Intent(context, CheckoutActivity.class)
                .putExtra(check.getClass().getName(), check);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        Check check = (Check) getIntent().getSerializableExtra(Check.class.getName());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_activity_content, CheckoutFragment.newInstance(check))
                .commit();
    }

}