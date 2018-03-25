package com.nz2dev.tenantcloudgoods.data.tools;

import com.google.gson.Gson;
import com.nz2dev.tenantcloudgoods.domain.models.GoodsIdentifier;
import com.nz2dev.tenantcloudgoods.domain.tools.Serializer;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by nz2Dev on 25.03.2018
 */
@Singleton
public class JsonSerializer implements Serializer {

    private final Gson gson;

    @Inject
    public JsonSerializer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public GoodsIdentifier deserializeGoodsIdentifier(String data) {
        return gson.fromJson(data, GoodsIdentifier.class);
    }

}
