package com.nz2dev.tenantcloudgoods.domain.interactors.orders;

import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 26.03.2018
 */
@Singleton
public class CreateCheckUserCase {

    private final SchedulersManager schedulers;

    @Inject
    public CreateCheckUserCase(SchedulersManager schedulers) {
        this.schedulers = schedulers;
    }

    public Single<Check> executor(Shop shop, List<Order> orderList) {
        return Single.just(Check.create(shop, orderList))
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
