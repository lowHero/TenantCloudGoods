package com.nz2dev.tenantcloudgoods.data.api;

import android.content.Context;

import com.nz2dev.tenantcloudgoods.data.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class StorageFilesAPI {

    private final Context context;

    @Inject
    public StorageFilesAPI(Context context) {
        this.context = context;
    }

    public void save(String path, String data) throws IOException {
        FileOutputStream outputStream = context.openFileOutput(path, Context.MODE_PRIVATE);
        outputStream.write(data.getBytes());
        outputStream.close();
    }

    public String load(String path) throws IOException {
        File file = new File(context.getFilesDir(), path);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new RuntimeException("can't create file");
            }
            return "";
        }

        FileInputStream inputStream = context.openFileInput(path);
        return IOUtils.toStringAndClose(inputStream);
    }

}
