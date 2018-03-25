package com.nz2dev.tenantcloudgoods.domain.interactors.orders;

import com.nz2dev.tenantcloudgoods.domain.exceptions.CheckDataGenerationException;
import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.nz2dev.tenantcloudgoods.domain.tools.Serializer;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 26.03.2018
 */
@Singleton
public class GenerateCheckDataUseCase {

    private final SchedulersManager schedulers;

    private final Serializer serializer;

    @Inject
    public GenerateCheckDataUseCase(SchedulersManager schedulers, Serializer serializer) {
        this.schedulers = schedulers;
        this.serializer = serializer;
    }

    public Single<String> executor(Check check) {
        return Single.just(check)
                .map(checkToSerialize -> {
                    String data = serializer.serializeCheckData(checkToSerialize);
                    if (data == null || data.length() == 0) {
                        throw new CheckDataGenerationException();
                    }
                    return data;
                })
                .subscribeOn(schedulers.getBackground())
                .observeOn(schedulers.getUI());
    }

}
