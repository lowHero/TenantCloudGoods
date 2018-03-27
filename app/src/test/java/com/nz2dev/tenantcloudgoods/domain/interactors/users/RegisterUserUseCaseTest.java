package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by nz2Dev on 24.03.2018
 */
@RunWith(MockitoJUnitRunner.class)
public class RegisterUserUseCaseTest {

    @Mock private UserRepository userRepository;

    private RegisterUserUseCase registerUserUseCase;

    @Before
    public void setUp() throws Exception {
//        registerUserUseCase = new RegisterUserUseCase(schedulers, userRepository);
    }

    @Test
    public void invoke_WithProperExternalId_ShouldAddUserToRepositoryWithTheSameExternalId() throws Exception {
//        final String externalId = "1";
//
//        registerUserUseCase.invoke(externalId);
//
//        verify(userRepository).addUser(User.createCustomer(externalId));
    }

}