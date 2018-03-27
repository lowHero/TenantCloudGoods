package com.nz2dev.tenantcloudgoods.domain.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.Payment;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 27.03.2018
 */
public interface PaymentHistory {

    Single<Boolean> add(Payment history);
    Single<List<Payment>> getAll();

}
