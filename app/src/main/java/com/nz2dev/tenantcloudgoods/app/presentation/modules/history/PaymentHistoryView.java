package com.nz2dev.tenantcloudgoods.app.presentation.modules.history;

import com.nz2dev.tenantcloudgoods.domain.models.Payment;

import java.util.List;

/**
 * Created by nz2Dev on 27.03.2018
 */
interface PaymentHistoryView {

    void showHistory(List<Payment> payments);

}
