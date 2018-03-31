package com.nz2dev.tenantcloudgoods.domain.interactors.orders;

import com.nz2dev.tenantcloudgoods.domain.exceptions.PerformPaymentFailException;
import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.CheckRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 27.03.2018
 */
@Singleton
public class PerformPaymentUseCase {

    private final SchedulersManager schedulers;

    private final CheckRepository checkRepository;

    @Inject
    public PerformPaymentUseCase(SchedulersManager schedulers, CheckRepository checkRepository) {
        this.schedulers = schedulers;
        this.checkRepository = checkRepository;
    }

    public Single<Check> executor(Check check, User user) {
        return checkRepository.add(check, user)
                .map(result -> {
                    if (!result) {
                        throw new PerformPaymentFailException(check);
                    }
                    return check;
                })
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
