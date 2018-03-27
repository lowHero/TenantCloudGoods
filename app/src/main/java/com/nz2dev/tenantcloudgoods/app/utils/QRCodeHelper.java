package com.nz2dev.tenantcloudgoods.app.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Created by nz2Dev on 27.03.2018
 */
public class QRCodeHelper {

    public static Bitmap encodeToBitmap(String text, int width, int height) {
        BitMatrix matrix;
        try {
            QRCodeWriter writer = new QRCodeWriter();
            matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException ex) {
            ex.printStackTrace();
            return null;
        }

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }

}
