package com.nz2dev.tenantcloudgoods.app.presentation.modules.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.Navigator;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN;

/**
 * Created by nz2Dev on 24.03.2018
 */
@SuppressLint("RestrictedApi")
public class GoogleSignInFragment extends Fragment implements GoogleSignInView {

    private static final int RC_SIGN_IN = 0x00000001;

    public static GoogleSignInFragment newInstance() {
        return new GoogleSignInFragment();
    }

    @BindView(R.id.btn_sign_in)
    SignInButton signInButton;

    @Inject GoogleSignInPresenter presenter;
    private GoogleSignInClient googleSignInClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dependencies.fromApplication(getContext())
                .createGoogleSignInComponent()
                .inject(this);

        googleSignInClient = GoogleSignIn.getClient(getActivity(),
                new GoogleSignInOptions.Builder(DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in_google, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        signInButton.setSize(SignInButton.SIZE_WIDE);
        presenter.setView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @OnClick(R.id.btn_sign_in)
    public void onSignInClick() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            presenter.handleSignInTask(task);
        }
    }

    @Override
    public void showApiError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateHome(User user) {
        Navigator.navigateHomeFrom(getActivity(), user);
    }

}
