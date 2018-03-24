package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by nz2Dev on 24.03.2018
 */
@Singleton
public class RegisterCustomersUseCase {

    private UserRepository userRepository;

    @Inject
    public RegisterCustomersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User invoke(String externalId) {
        // check if user already registered and throw exceptions.
        // ...

        User user = User.createCustomer(externalId);
        userRepository.addUser(user);

        return user;
    }

}
