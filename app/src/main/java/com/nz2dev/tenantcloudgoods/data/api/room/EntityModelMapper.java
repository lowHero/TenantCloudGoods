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

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by nz2Dev on 31.03.2018
 */
public final class EntityModelMapper {

    public static <E, M> List<M> toModelsList(List<E> entities, Function<E, M> mapper) {
        return Observable.fromIterable(entities)
                .map(mapper)
                .toList()
                .blockingGet();
    }

    public static Goods toGoods(GoodsEntity goodsEntity) {
        return new Goods(
                goodsEntity.id,
                goodsEntity.shopId,
                goodsEntity.name,
                goodsEntity.imageUrl,
                goodsEntity.price,
                goodsEntity.availableAmount
        );
    }

    public static Shop toShop(ShopEntity shopEntity) {
        return new Shop(
                shopEntity.id,
                shopEntity.name
        );
    }

    public static User toUser(UserEntity entity) {
        return new User(
                entity.id,
                entity.externalId,
                entity.admin
        );
    }

    public static Order toOrder(OrderEntity orderEntity, GoodsEntity goodsEntity) {
        return new Order(
                orderEntity.id,
                toGoods(goodsEntity),
                orderEntity.goodsAmount,
                orderEntity.totalPrice
        );
    }

    public static Check toCheck(CheckEntity checkEntity, ShopEntity shopEntity, List<Order> orders) {
        return new Check(
                checkEntity.id,
                toShop(shopEntity),
                checkEntity.time,
                orders
        );
    }

}
