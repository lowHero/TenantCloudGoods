package com.nz2dev.tenantcloudgoods.device;

import com.nz2dev.tenantcloudgoods.device.execution.AndroidSchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Module
public class DeviceModule {

    @Provides
    @Singleton
    SchedulersManager provideSchedulersManager(AndroidSchedulersManager androidSchedulersManager) {
        return androidSchedulersManager;
    }

}
