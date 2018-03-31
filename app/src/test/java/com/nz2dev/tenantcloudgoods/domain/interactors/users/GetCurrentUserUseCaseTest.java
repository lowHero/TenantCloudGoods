package com.nz2dev.tenantcloudgoods.domain.interactors.users;

import com.nz2dev.tenantcloudgoods.domain.exceptions.ExternalIdNotSetException;
import com.nz2dev.tenantcloudgoods.domain.exceptions.UserNotRegisteredException;
import com.nz2dev.tenantcloudgoods.domain.execution.SchedulersManager;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.nz2dev.tenantcloudgoods.domain.preferences.AccountPreferences;
import com.nz2dev.tenantcloudgoods.domain.repositories.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

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

    @Mock private SchedulersManager schedulers;
    @Mock private UserRepository userRepository;
    @Mock private AccountPreferences accountPreferences;

    private GetCurrentUserUseCase getCurrentUserUseCase;

    @Before
    public void init() {
        getCurrentUserUseCase = new GetCurrentUserUseCase(schedulers, userRepository, accountPreferences);

        when(schedulers.getBackground()).thenReturn(Schedulers.trampoline());
        when(schedulers.getUI()).thenReturn(Schedulers.trampoline());
    }

    @Test
    public void invoke_AccountPreferencesHasNotExternalId_ShouldThrowExternalIdNotSetException() throws Exception {
        TestObserver<User> testObserver = new TestObserver<>();
        when(accountPreferences.getExternalId()).thenReturn(null);

        getCurrentUserUseCase.executor().subscribe(testObserver);

        testObserver
                .assertComplete()
                .assertError(ExternalIdNotSetException.class);
    }

    @Test
    public void invoke_UserNotExistInRepository_ShouldThrowUserNotRegisteredException() {
        TestObserver<User> testObserver = new TestObserver<>();

        when(accountPreferences.getExternalId()).thenReturn("1");
        when(userRepository.getUser(eq("1"))).thenReturn(null);
        getCurrentUserUseCase.executor().subscribe(testObserver);

        testObserver
                .assertComplete()
                .assertError(UserNotRegisteredException.class);
    }

}