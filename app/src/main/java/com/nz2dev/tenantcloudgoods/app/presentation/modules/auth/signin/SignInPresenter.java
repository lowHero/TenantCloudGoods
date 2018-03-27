package com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signin;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.app.utils.ThrowableUtils;
import com.nz2dev.tenantcloudgoods.domain.exceptions.UserNotRegisteredException;
import com.nz2dev.tenantcloudgoods.domain.interactors.users.GetUserByExternalIdUseCase;
import com.nz2dev.tenantcloudgoods.domain.interactors.users.SignInUserUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.nz2dev.tenantcloudgoods.app.utils.Constants.SIGN_IN_GOOGLE_ACCOUNT_STATUS_CODE_CANCEL;

/**
 * Created by nz2Dev on 24.03.2018
 */
@PerFragment
class SignInPresenter extends DisposableBasePresenter<SignInView> {

    private GetUserByExternalIdUseCase getUserByExternalIdUseCase;
    private SignInUserUseCase signInUserUseCase;

    private User pendingUser;
    private boolean silentSignInAfterSignOutEnabled;

    @Inject
    SignInPresenter(GetUserByExternalIdUseCase getUserByExternalIdUseCase, SignInUserUseCase signInUserUseCase) {
        this.getUserByExternalIdUseCase = getUserByExternalIdUseCase;
        this.signInUserUseCase = signInUserUseCase;
    }

    void checkLastGoogleAccount(GoogleSignInAccount lastAccount) {
        if (lastAccount == null) {
            getView().showAccountSelected(false);
        } else {
            checkAccount(lastAccount);
        }
    }

    void changeAccount() {
        silentSignInAfterSignOutEnabled = true;
        getView().navigateSignOutGoogleAccount();
    }

    void chooseAccount() {
        getView().navigateSignInGoogleAccount();
    }

    void handleGoogleSignInTask(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            checkAccount(account);
        } catch (ApiException e) {
            if (e.getStatusCode() != SIGN_IN_GOOGLE_ACCOUNT_STATUS_CODE_CANCEL) {
                getView().showError(R.string.error_google_api_exception_with_code, e.getStatusCode());
            }
        }
    }

    void handleGoogleSignOutTask(Task<Void> signOutTask) {
        signOutTask.addOnFailureListener(e -> {
            if (e instanceof ApiException) {
                ApiException apiException = (ApiException) e;
                getView().showError(R.string.error_google_api_exception_with_code, apiException.getStatusCode());
            }
        });
        signOutTask.addOnSuccessListener(command -> {
            getView().showAccountSelected(false);
            if (silentSignInAfterSignOutEnabled) {
                silentSignInAfterSignOutEnabled = false;
                getView().navigateSignInGoogleAccount();
            }
        });
    }

    void confirmClick() {
        getView().navigateHome(pendingUser);
    }

    private void checkAccount(GoogleSignInAccount googleAccount) {
        manage("Checking", getUserByExternalIdUseCase
                .executor(googleAccount.getEmail())
                .flatMap(signInUserUseCase::executor)
                .subscribe(user -> {
                    pendingUser = user;
                    getView().showAccountSelected(true);
                    getView().showAccountAndUserData(googleAccount, user);
                }, throwable -> {
                    if (throwable instanceof UserNotRegisteredException) {
                        getView().navigateSignOutGoogleAccount();
                        getView().showAccountNotRegistered();
                    } else {
                        ThrowableUtils.handleUncaught(throwable);
                    }
                }));
    }

}
