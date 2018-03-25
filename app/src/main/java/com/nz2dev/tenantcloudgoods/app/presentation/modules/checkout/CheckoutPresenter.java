package com.nz2dev.tenantcloudgoods.app.presentation.modules.checkout;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.exceptions.CheckDataGenerationException;
import com.nz2dev.tenantcloudgoods.domain.interactors.orders.GenerateCheckDataUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.Check;

import javax.inject.Inject;

import static com.nz2dev.tenantcloudgoods.app.utils.ThrowableUtils.handleUncaught;

/**
 * Created by nz2Dev on 26.03.2018
 */
@PerFragment
public class CheckoutPresenter extends DisposableBasePresenter<CheckoutView> {

    private final GenerateCheckDataUseCase generateCheckDataUseCase;

    @Inject
    public CheckoutPresenter(GenerateCheckDataUseCase generateCheckDataUseCase) {
        this.generateCheckDataUseCase = generateCheckDataUseCase;
    }

    void prepare(Check check) {
        manage("Generation", generateCheckDataUseCase
                .executor(check)
                .subscribe(checkData -> {
                    getView().showCheckAmount(check.getAmount());
                    getView().showGeneratedData(encodeToBitmap(checkData));
                }, throwable -> {
                    if (throwable instanceof CheckDataGenerationException) {
                        getView().showGenerationFailed();
                    } else {
                        handleUncaught(throwable);
                    }
                }));
    }

    private Bitmap encodeToBitmap(String data) throws WriterException {
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        return barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, 512, 512);
    }

}