package com.nz2dev.tenantcloudgoods.domain.exceptions;

/**
 * Created by nz2Dev on 26.03.2018
 */
public class UserIsAlreadyRegistered extends RuntimeException {

    public UserIsAlreadyRegistered(String externalId) {
        super(externalId);
    }

}
