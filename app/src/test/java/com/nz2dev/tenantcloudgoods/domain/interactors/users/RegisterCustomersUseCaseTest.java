package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by nz2Dev on 24.03.2018
 */
@RunWith(MockitoJUnitRunner.class)
public class RegisterCustomersUseCaseTest {

    @Mock private UserRepository userRepository;

    private RegisterCustomersUseCase registerCustomersUseCase;

    @Before
    public void setUp() throws Exception {
        registerCustomersUseCase = new RegisterCustomersUseCase(userRepository);
    }

    @Test
    public void invoke_WithProperExternalId_ShouldAddUserToRepositoryWithTheSameExternalId() throws Exception {
        final String externalId = "1";

        registerCustomersUseCase.invoke(externalId);

        verify(userRepository).addUser(User.createCustomer(externalId));
    }

}