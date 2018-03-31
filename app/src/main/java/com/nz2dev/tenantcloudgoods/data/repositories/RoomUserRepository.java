package com.nz2dev.tenantcloudgoods.data.repositories;

import com.nz2dev.tenantcloudgoods.data.api.room.EntityModelMapper;
import com.nz2dev.tenantcloudgoods.data.api.room.ModelEntityMapper;
import com.nz2dev.tenantcloudgoods.data.api.room.TenantCloudGoodsDatabase;
import com.nz2dev.tenantcloudgoods.data.api.room.dao.UserDao;
import com.nz2dev.tenantcloudgoods.data.api.room.entity.UserEntity;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by nz2Dev on 31.03.2018
 */
@Singleton
public class RoomUserRepository implements UserRepository {

    private final UserDao userDao;

    @Inject
    public RoomUserRepository(TenantCloudGoodsDatabase database) {
        this.userDao = database.getUserDao();
    }

    @Override
    public Single<User> addUser(User user) {
        return Single.create(emitter -> {
            UserEntity entity = ModelEntityMapper.toUserEntity(user);

            long generatedId = userDao.add(entity);
            user.setId(generatedId);

            emitter.onSuccess(user);
        });
    }

    @Override
    public Single<User> getUser(String externalId) {
        return Single.create(emitter -> {
            UserEntity entity = userDao.getByExternalId(externalId);

            if (entity == null) {
                emitter.onSuccess(User.createEmptyExternalIdHolder(externalId));
            } else {
                User user = EntityModelMapper.toUser(entity);
                emitter.onSuccess(user);
            }
        });
    }
}
