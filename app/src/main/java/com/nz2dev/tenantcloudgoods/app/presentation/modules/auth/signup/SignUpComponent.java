package com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signup;

import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;

import dagger.Subcomponent;

/**
 * Created by nz2Dev on 24.03.2018
 */
@PerFragment
@Subcomponent
public interface SignUpComponent {
    void inject(SignUpFragment signInFragment);
}
