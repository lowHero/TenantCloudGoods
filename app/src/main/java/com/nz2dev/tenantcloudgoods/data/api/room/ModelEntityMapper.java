package com.nz2dev.tenantcloudgoods.data.api.room;

import com.nz2dev.tenantcloudgoods.data.api.room.entity.CheckEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.GoodsEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.OrderEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.ShopEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.UserEntity;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;

/**
 * Created by nz2Dev on 31.03.2018
 */
public final class ModelEntityMapper {

    public static GoodsEntity toGoodsEntity(Goods goods) {
        return new GoodsEntity(
                goods.getId(),
                goods.getShopId(),
                goods.getName(),
                goods.getImageUrl(),
                goods.getPrice(),
                goods.getAvailableAmount()
        );
    }

    public static ShopEntity toShopEntity(Shop shop) {
        return new ShopEntity(
                shop.getId(),
                shop.getName()
        );
    }

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getExternalId(),
                user.isAdmin()
        );
    }

    public static CheckEntity toCheckEntity(Check check, User user) {
        return new CheckEntity(
                check.getId(),
                check.getShop().getId(),
                user.getId(),
                check.getTime());
    }

    public static OrderEntity toOrderEntity(Order order) {
        return new OrderEntity(
                order.getId(),
                order.getGoods().getId(),
                order.getGoodsAmount(),
                order.getTotalPrice()
        );
    }

}
