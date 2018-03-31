package com.nz2dev.tenantcloudgoods.data.repositories;

import com.nz2dev.tenantcloudgoods.data.api.room.EntityModelMapper;
import com.nz2dev.tenantcloudgoods.data.api.room.TenantCloudGoodsDatabase;
import com.nz2dev.tenantcloudgoods.data.api.room.dao.CheckDao;
import com.nz2dev.tenantcloudgoods.data.api.room.dao.GoodsDao;
import com.nz2dev.tenantcloudgoods.data.api.room.dao.OrdersDao;
import com.nz2dev.tenantcloudgoods.data.api.room.dao.ShopDao;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.CheckEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.CheckOrderMapping;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.OrderEntity;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.ShopEntity;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.CheckRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

import static com.nz2dev.tenantcloudgoods.data.api.room.EntityModelMapper.toModelsList;
import static com.nz2dev.tenantcloudgoods.data.api.room.EntityModelMapper.toOrder;
import static com.nz2dev.tenantcloudgoods.data.api.room.ModelEntityMapper.toCheckEntity;
import static com.nz2dev.tenantcloudgoods.data.api.room.ModelEntityMapper.toOrderEntity;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Singleton
public class RoomCheckRepository implements CheckRepository {

    private final CheckDao checkDao;
    private final ShopDao shopDao;
    private final OrdersDao ordersDao;
    private final GoodsDao goodsDao;

    @Inject
    public RoomCheckRepository(TenantCloudGoodsDatabase database) {
        ordersDao = database.getOrdersDao();
        checkDao = database.getCheckDao();
        goodsDao = database.getGoodsDao();
        shopDao = database.getShopDao();
    }

    @Override
    public Single<Boolean> add(Check check, User user) {
        return Single.create(emitter -> {
            CheckEntity checkEntity = toCheckEntity(check, user);
            long generatedCheckId = checkDao.add(checkEntity);

            for (Order order : check.getOrders()) {
                OrderEntity orderEntity = toOrderEntity(order);
                long generatedOrderId = ordersDao.add(orderEntity);
                ordersDao.addCheckOrderMapping(CheckOrderMapping.create(generatedCheckId, generatedOrderId));
            }

            emitter.onSuccess(generatedCheckId >= 0);
        });
    }

    @Override
    public Single<List<Check>> getAll(User user) {
        return Single.create(emitter -> {
            List<CheckEntity> checkEntities = checkDao.getAllByUserId(user.getId());

            List<Check> checks = toModelsList(checkEntities, checkEntity -> {
                ShopEntity shopEntity = shopDao.getById(checkEntity.shopId);
                List<OrderEntity> orderEntities = ordersDao.getOrdersByCheckId(checkEntity.id);

                List<Order> orders = toModelsList(orderEntities, orderEntity
                        -> toOrder(orderEntity, goodsDao.getById(orderEntity.goodsId)));

                return EntityModelMapper.toCheck(checkEntity, shopEntity, orders);
            });

            emitter.onSuccess(checks);
        });
    }

}
