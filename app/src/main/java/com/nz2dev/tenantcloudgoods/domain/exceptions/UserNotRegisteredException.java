package com.nz2dev.tenantcloudgoods.domain.exceptions;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class UserNotRegisteredException extends RuntimeException {

    public UserNotRegisteredException(String externalId) {
        super("externalId = " + externalId);
    }

}
