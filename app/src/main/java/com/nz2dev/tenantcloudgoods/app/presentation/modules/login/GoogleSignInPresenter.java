package com.nz2dev.tenantcloudgoods.app.presentation.modules.login;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.BasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.interactors.users.GetUserByExternalIdUseCase;
import com.nz2dev.tenantcloudgoods.domain.interactors.users.RegisterCustomersUseCase;
import com.nz2dev.tenantcloudgoods.domain.interactors.users.SignInUserUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import javax.inject.Inject;

/**
 * Created by nz2Dev on 24.03.2018
 */
@PerFragment
public class GoogleSignInPresenter extends BasePresenter<GoogleSignInView> {

    private GetUserByExternalIdUseCase getUserByExternalIdUseCase;
    private RegisterCustomersUseCase registerCustomersUseCase;
    private SignInUserUseCase signInUserUseCase;

    @Inject
    GoogleSignInPresenter(GetUserByExternalIdUseCase getUserByExternalIdUseCase, RegisterCustomersUseCase registerCustomersUseCase, SignInUserUseCase signInUserUseCase) {
        this.getUserByExternalIdUseCase = getUserByExternalIdUseCase;
        this.registerCustomersUseCase = registerCustomersUseCase;
        this.signInUserUseCase = signInUserUseCase;
    }

    void handleSignInTask(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            checkAccount(account);
        } catch (ApiException e) {
            getView().showApiError(String.valueOf(e.getStatusCode()));
        }
    }

    private void checkAccount(GoogleSignInAccount googleAccount) {
        User user = getUserByExternalIdUseCase.invoke(googleAccount.getId());
        if (user == null) {
            user = registerCustomersUseCase.invoke(googleAccount.getId());
        }
        signInUserUseCase.invoke(user);
        getView().navigateHome(user);
    }

}
