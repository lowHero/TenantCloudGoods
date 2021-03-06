package com.nz2dev.tenantcloudgoods.domain.tools;

import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.models.GoodsIdentifier;

/**
 * Created by nz2Dev on 25.03.2018
 */
public interface Serializer {

    GoodsIdentifier deserializeGoodsIdentifier(String data);
    Goods deserializeGoods(String data);
    String serializeCheckData(Check check);

}
