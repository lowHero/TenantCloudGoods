package com.nz2dev.tenantcloudgoods.domain.exceptions;

import com.nz2dev.tenantcloudgoods.domain.models.Check;

/**
 * Created by nz2Dev on 27.03.2018
 */
public class SavePaymentToHistoryFailException extends Exception {

    public SavePaymentToHistoryFailException(Check check) {
        super(check.toString());
    }

}
