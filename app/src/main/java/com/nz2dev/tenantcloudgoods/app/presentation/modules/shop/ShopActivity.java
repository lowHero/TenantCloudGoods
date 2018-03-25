package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer.CustomerShopFragment;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 25.03.2018
 */
public class ShopActivity extends AppCompatActivity {

    public static Intent getCallingIntent(Context context, Shop shop, User user) {
        return new Intent(context, ShopActivity.class)
                .putExtra(shop.getClass().getName(), shop)
                .putExtra(user.getClass().getName(), user);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        Shop shop = (Shop) getIntent().getSerializableExtra(Shop.class.getName());
        User user = (User) getIntent().getSerializableExtra(User.class.getName());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_activity_content, CustomerShopFragment.newInstance(shop, user))
                .commit();
    }

}