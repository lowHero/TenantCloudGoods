package com.nz2dev.tenantcloudgoods.device.execution;

import android.support.annotation.NonNull;

import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class AndroidSchedulersManager implements SchedulersManager, Executor {

    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    public AndroidSchedulersManager() {
        threadPoolExecutor = new ThreadPoolExecutor(4, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        threadPoolExecutor.execute(command);
    }

    @Override
    public Scheduler getBackground() {
        return Schedulers.from(this);
    }

    @Override
    public Scheduler getUI() {
        return AndroidSchedulers.mainThread();
    }

}
