package com.nz2dev.tenantcloudgoods.app.presentation.modules.launch;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.domain.exceptions.ExternalIdNotSetException;
import com.nz2dev.tenantcloudgoods.domain.exceptions.UserNotRegisteredException;
import com.nz2dev.tenantcloudgoods.domain.interactors.users.GetCurrentUserUseCase;

import javax.inject.Inject;

import static com.nz2dev.tenantcloudgoods.app.utils.ThrowableUtils.handleUncaught;

/**
 * Created by nz2Dev on 24.03.2018
 */
// TODO investigate behaviour when presenter will be unscoped
class LaunchPresenter extends DisposableBasePresenter<LaunchView> {

    private final GetCurrentUserUseCase getCurrentUserUseCase;

    @Inject
    LaunchPresenter(GetCurrentUserUseCase getCurrentUserUseCase) {
        this.getCurrentUserUseCase = getCurrentUserUseCase;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        manage(getCurrentUserUseCase
                .executor()
                .subscribe(getView()::navigateHome, t -> {
                    if (t instanceof ExternalIdNotSetException || t instanceof UserNotRegisteredException) {
                        getView().navigateSignIn();
                    } else {
                        handleUncaught(t);
                    }
                }));
    }

}