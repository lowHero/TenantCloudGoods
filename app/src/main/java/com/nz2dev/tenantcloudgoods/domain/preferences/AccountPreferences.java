package com.nz2dev.tenantcloudgoods.domain.preferences;

/**
 * Created by nz2Dev on 24.03.2018
 */
public interface AccountPreferences {

    String getExternalId();
    void saveExternalId(String externalId);

}
