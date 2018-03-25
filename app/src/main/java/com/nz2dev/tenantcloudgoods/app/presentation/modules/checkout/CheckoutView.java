package com.nz2dev.tenantcloudgoods.app.presentation.modules.checkout;

import android.graphics.Bitmap;

/**
 * Created by nz2Dev on 26.03.2018
 */
interface CheckoutView {

    void showGeneratedData(Bitmap checkBitmap);
    void showCheckAmount(float amount);
    void showGenerationFailed();

}
