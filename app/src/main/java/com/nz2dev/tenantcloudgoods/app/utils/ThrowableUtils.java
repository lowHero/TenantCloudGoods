package com.nz2dev.tenantcloudgoods.app.utils;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class ThrowableUtils {

    public static void handleUncaught(Throwable throwable) {
        Thread currentThread = Thread.currentThread();
        Thread.UncaughtExceptionHandler handler = currentThread.getUncaughtExceptionHandler();
        handler.uncaughtException(currentThread, throwable);
    }

}
