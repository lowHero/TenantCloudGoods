package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.exceptions.ExternalIdNotSetException;
import com.nz2dev.tenantcloudgoods.domain.exceptions.UserNotRegisteredException;
import com.nz2dev.tenantcloudgoods.domain.preferences.AccountPreferences;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by nz2Dev on 24.03.2018
 */
@RunWith(MockitoJUnitRunner.class)
public class GetCurrentUserUseCaseTest {

    @Mock private UserRepository userRepository;
    @Mock private AccountPreferences accountPreferences;

    private GetCurrentUserUseCase getCurrentUserUseCase;

    @Before
    public void init() {
        getCurrentUserUseCase = new GetCurrentUserUseCase(userRepository, accountPreferences);
    }

    @Test
    public void invoke_AccountPreferencesHasNotExternalId_ShouldThrowExternalIdNotSetException() throws Exception {
        when(accountPreferences.getExternalId()).thenReturn(null);
        try {
            getCurrentUserUseCase.invoke();
            failBecauseExceptionWasNotThrown(ExternalIdNotSetException.class);
        } catch (ExternalIdNotSetException e) {
            assertThat(e).hasNoCause();
        }
    }

    @Test
    public void invoke_UserNotExistInRepository_ShouldThrowUserNotRegisteredException() {
        when(accountPreferences.getExternalId()).thenReturn("1");
        when(userRepository.getUser(eq("1"))).thenReturn(null);
        try {
            getCurrentUserUseCase.invoke();
            failBecauseExceptionWasNotThrown(UserNotRegisteredException.class);
        } catch (UserNotRegisteredException e) {
            assertThat(e).hasNoCause();
        }
    }

}