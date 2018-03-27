package com.nz2dev.tenantcloudgoods.domain.interactors.orders;

import com.nz2dev.tenantcloudgoods.domain.models.Check;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 27.03.2018
 */
@Singleton
public class SaveCheckToHistoryUseCase {

    @Inject
    public SaveCheckToHistoryUseCase() {
    }

    public Single<Check> executor(Check check) {
        return Single.just(check);
    }

}
