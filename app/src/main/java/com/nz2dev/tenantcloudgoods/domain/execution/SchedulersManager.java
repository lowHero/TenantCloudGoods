package com.nz2dev.tenantcloudgoods.domain.execution;

import io.reactivex.Scheduler;

/**
 * Created by nz2Dev on 24.03.2018
 */
public interface SchedulersManager {

    Scheduler getBackground();
    Scheduler getUI();

}
