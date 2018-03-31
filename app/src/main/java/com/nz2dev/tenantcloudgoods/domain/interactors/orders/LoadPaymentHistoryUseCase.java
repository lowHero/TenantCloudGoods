package com.nz2dev.tenantcloudgoods.domain.interactors.orders;

import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.CheckRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 27.03.2018
 */
@Singleton
public class LoadPaymentHistoryUseCase {

    private final SchedulersManager schedulers;

    private final CheckRepository checkRepository;

    @Inject
    public LoadPaymentHistoryUseCase(SchedulersManager schedulers, CheckRepository checkRepository) {
        this.schedulers = schedulers;
        this.checkRepository = checkRepository;
    }

    public Single<List<Check>> executor(User user) {
        return checkRepository.getAll(user)
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
