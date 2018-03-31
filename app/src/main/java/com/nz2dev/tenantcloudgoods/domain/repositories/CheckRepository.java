package com.nz2dev.tenantcloudgoods.domain.repositories;

import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 27.03.2018
 */
public interface CheckRepository {

    Single<Boolean> add(Check check, User user);
    Single<List<Check>> getAll(User user);

}
