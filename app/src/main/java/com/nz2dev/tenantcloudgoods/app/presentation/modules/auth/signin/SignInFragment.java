package com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.Navigator;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signup.SignUpFragment;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.nz2dev.tenantcloudgoods.app.utils.FragmentHelper.getLastBackStackName;

/**
 * Created by nz2Dev on 24.03.2018
 */
@SuppressLint("RestrictedApi")
public class SignInFragment extends Fragment implements SignInView {

    public static final String SIGN_UP_NAVIGATION = "SignIn->SignUp";
    private static final int RC_SIGN_IN = 0x00000001;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.btn_choose_account) SignInButton chooseAccountButton;
    @BindView(R.id.tv_account_name) TextView accountName;
    @BindView(R.id.tv_change_account) TextView changeAccountText;
    @BindView(R.id.fl_role_container) FrameLayout roleContainer;
    @BindView(R.id.iv_delete_user) ImageView deleteUserIcon;
    @BindView(R.id.iv_navigate_next) ImageView navigateNextIcon;

    @Inject SignInPresenter presenter;
    @Inject GoogleSignInClient googleSignInClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Dependencies.fromApplication(getContext())
                .createSignInComponent()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        presenter.setView(this);
        presenter.checkLastGoogleAccount(GoogleSignIn.getLastSignedInAccount(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity.getSupportActionBar() != null) {
            boolean hasBackStack = getFragmentManager().getBackStackEntryCount() > 0;
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackStack);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            presenter.handleGoogleSignInTask(task);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.tv_change_account)
    public void onChangeAccountClick() {
        presenter.changeAccount();
    }

    @OnClick(R.id.btn_choose_account)
    public void onChooseAccountClick() {
        presenter.chooseAccount();
    }

    @OnClick(R.id.iv_navigate_next)
    public void setNavigateNextClick() {
        presenter.confirmClick();
    }

    @OnClick(R.id.tv_go_sign_up)
    public void onNavigateSignUp() {
        if (SignUpFragment.SIGN_IN_NAVIGATION.equals(getLastBackStackName(getFragmentManager()))) {
            getFragmentManager().popBackStack();
        } else {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.fl_activity_content, SignUpFragment.newInstance())
                    .addToBackStack(SIGN_UP_NAVIGATION)
                    .commit();
        }
    }

    @Override
    public void showError(@StringRes int templateResId, Object... templateParams) {
        String text = String.format(Locale.getDefault(), getContext().getString(templateResId), templateParams);
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAccountNotRegistered() {
        Toast.makeText(getContext(), "Account not registered!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAccountSelected(boolean selected) {
        accountName.setVisibility(selected ? View.VISIBLE : View.GONE);
        changeAccountText.setVisibility(selected ? View.VISIBLE : View.GONE);
        roleContainer.setVisibility(selected ? View.VISIBLE : View.GONE);
        deleteUserIcon.setVisibility(selected ? View.VISIBLE : View.GONE);
        navigateNextIcon.setVisibility(selected ? View.VISIBLE : View.GONE);

        chooseAccountButton.setVisibility(selected ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showAccountAndUserData(GoogleSignInAccount googleAccount, User user) {
        int roleLayoutId = user.isAdmin() ? R.layout.include_user_admin : R.layout.include_user_customer;
        View view = LayoutInflater.from(getContext()).inflate(roleLayoutId, roleContainer, false);

        accountName.setText(googleAccount.getDisplayName());
        roleContainer.removeAllViews();
        roleContainer.addView(view);
    }

    @Override
    public void navigateSignInGoogleAccount() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void navigateSignOutGoogleAccount() {
        Task<Void> signOutTask = googleSignInClient.signOut();
        presenter.handleGoogleSignOutTask(signOutTask);
    }

    @Override
    public void navigateHome(User user) {
        Navigator.navigateHomeFrom(getActivity(), user);
    }

}
