package com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signup;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.app.utils.ThrowableUtils;
import com.nz2dev.tenantcloudgoods.domain.exceptions.UserIsAlreadyRegistered;
import com.nz2dev.tenantcloudgoods.domain.interactors.users.RegisterUserUseCase;
import com.nz2dev.tenantcloudgoods.domain.interactors.users.SignInUserUseCase;

import javax.inject.Inject;

import static com.nz2dev.tenantcloudgoods.app.utils.Constants.SIGN_IN_GOOGLE_ACCOUNT_STATUS_CODE_CANCEL;

/**
 * Created by nz2Dev on 24.03.2018
 */
@PerFragment
class SignUpPresenter extends DisposableBasePresenter<SignUpView> {

    private final RegisterUserUseCase registerUserUseCase;

    private GoogleSignInAccount pendingGoogleAccount;
    private boolean silentSignInAfterSignOutEnabled;
    private boolean adminSelected;

    @Inject
    SignUpPresenter(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        getView().navigateSignOutGoogleAccount();
        getView().showAccountSelected(false);
        getView().showAdminSelected(adminSelected = false);
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
            pendingGoogleAccount = task.getResult(ApiException.class);
            getView().showAccountSelected(true);
            getView().showAccount(pendingGoogleAccount);
        } catch (ApiException e) {
            if (e.getStatusCode() != SIGN_IN_GOOGLE_ACCOUNT_STATUS_CODE_CANCEL) {
                getView().showError(R.string.error_google_api_exception_with_code, e.getStatusCode());
            }
        }
    }

    void handleGoogleSignOutTask(Task<Void> task) {
        task.addOnFailureListener(e -> {
            if (e instanceof ApiException) {
                ApiException apiException = (ApiException) e;
                getView().showError(R.string.error_google_api_exception_with_code, apiException.getStatusCode());
            }
        });
        task.addOnSuccessListener(command -> {
            getView().showAccountSelected(false);
            if (silentSignInAfterSignOutEnabled) {
                silentSignInAfterSignOutEnabled = false;
                getView().navigateSignInGoogleAccount();
            }
        });
    }

    void adminRoleSelected(boolean selected) {
        getView().showAdminSelected(adminSelected = selected);
    }

    void signUpClick() {
        manage("Registering", registerUserUseCase
                .executor(pendingGoogleAccount.getEmail(), adminSelected)
                .subscribe(user -> getView().navigateSignIn(), throwable -> {
                    if (throwable instanceof UserIsAlreadyRegistered) {
                        getView().showError(R.string.error_user_is_already_registered);
                    } else {
                        ThrowableUtils.handleUncaught(throwable);
                    }
                }));
    }

}