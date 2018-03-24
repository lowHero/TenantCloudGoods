package com.nz2dev.tenantcloudgoods.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.nz2dev.tenantcloudgoods.domain.preferences.AccountPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class SharedAccountPreferences implements AccountPreferences {

    private static final String PREFERENCES_NAME = "account_preferences";
    private static final String KEY_EXTERNAL_ID = "external_id";

    private final SharedPreferences sharedPreferences;

    @Inject
    public SharedAccountPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
    }

    @Override
    public String getExternalId() {
        return sharedPreferences.getString(KEY_EXTERNAL_ID, EMPTY_EXTERNAL_ID);
    }

    @Override
    public void saveExternalId(String externalId) {
        sharedPreferences.edit()
                .putString(KEY_EXTERNAL_ID, externalId)
                .apply();
    }

}
