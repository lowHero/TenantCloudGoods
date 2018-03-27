package com.nz2dev.tenantcloudgoods.domain.models;

/**
 * Created by nz2Dev on 27.03.2018
 */
public class Payment {

    public static Payment createSinceNow(Check check) {
        return new Payment(check, System.currentTimeMillis());
    }

    private Check check;
    private long time;

    public Payment(Check check, long time) {
        this.check = check;
        this.time = time;
    }

    public Check getCheck() {
        return check;
    }

    public long getTime() {
        return time;
    }

}
