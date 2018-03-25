package com.nz2dev.tenantcloudgoods.domain.tools;

import com.nz2dev.tenantcloudgoods.domain.models.GoodsIdentifier;

/**
 * Created by nz2Dev on 25.03.2018
 */
public interface Serializer {

    GoodsIdentifier deserializeGoodsIdentifier(String data);

}
