package com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.auth.signin.SignInFragment;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;

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
public class SignUpFragment extends Fragment implements SignUpView {

    public static final String SIGN_IN_NAVIGATION = "SignUp->SignIn";
    private static final int RC_SIGN_IN_GOOGLE = 0x00000001;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.btn_choose_account) SignInButton chooseAccountButton;
    @BindView(R.id.tv_account_name) TextView accountNameText;
    @BindView(R.id.tv_change_account) TextView changeAccountText;
    @BindView(R.id.tv_hint_choose_role) TextView chooseRoleHint;
    @BindView(R.id.admin_root) View adminRole;
    @BindView(R.id.customer_root) View customerRole;
    @BindView(R.id.iv_sign_up) ImageView signUpIcon;

    @Inject SignUpPresenter presenter;
    @Inject GoogleSignInClient googleSignInClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Dependencies.fromApplication(getContext())
                .createSignUpComponent()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        presenter.setView(this);
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
        if (requestCode == RC_SIGN_IN_GOOGLE) {
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

    @OnClick(R.id.btn_choose_account)
    public void onChooseAccountClick() {
        presenter.chooseAccount();
    }

    @OnClick(R.id.tv_change_account)
    public void onChangeAccountClick() {
        presenter.changeAccount();
    }

    @OnClick(R.id.admin_root)
    public void onAdminRoleClick() {
        presenter.adminRoleSelected(true);
    }

    @OnClick(R.id.customer_root)
    public void onCustomerRoleClick() {
        presenter.adminRoleSelected(false);
    }

    @OnClick(R.id.iv_sign_up)
    public void onSignUpClick() {
        presenter.signUpClick();
    }

    @OnClick(R.id.tv_go_sign_in)
    public void onNavigateSignInClick() {
        if (SignInFragment.SIGN_UP_NAVIGATION.equals(getLastBackStackName(getFragmentManager()))) {
            getFragmentManager().popBackStack();
        } else {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.fl_activity_content, SignInFragment.newInstance())
                    .addToBackStack(SIGN_IN_NAVIGATION)
                    .commit();
        }
    }

    @Override
    public void showError(@StringRes int templateResId, Object... templateParams) {
        String text = String.format(Locale.getDefault(), getContext().getString(templateResId), templateParams);
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAccountSelected(boolean selected) {
        accountNameText.setVisibility(selected ? View.VISIBLE : View.GONE);
        changeAccountText.setVisibility(selected ? View.VISIBLE : View.GONE);
        chooseRoleHint.setVisibility(selected ? View.VISIBLE : View.GONE);
        adminRole.setVisibility(selected ? View.VISIBLE : View.GONE);
        customerRole.setVisibility(selected ? View.VISIBLE : View.GONE);
        signUpIcon.setVisibility(selected ? View.VISIBLE : View.GONE);

        chooseAccountButton.setVisibility(selected ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showAdminSelected(boolean selected) {
        int colorItemSelected = ContextCompat.getColor(getContext(), R.color.colorItemSelected);
        adminRole.setBackgroundColor(selected ? colorItemSelected : Color.TRANSPARENT);
        customerRole.setBackgroundColor(selected ? Color.TRANSPARENT : colorItemSelected);
    }

    @Override
    public void showAccount(GoogleSignInAccount googleAccount) {
        accountNameText.setText(googleAccount.getDisplayName());
    }

    @Override
    public void navigateSignInGoogleAccount() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN_GOOGLE);
    }

    @Override
    public void navigateSignOutGoogleAccount() {
        Task<Void> signOutTask = googleSignInClient.signOut();
        presenter.handleGoogleSignOutTask(signOutTask);
    }

    @Override
    public void navigateSignIn() {
        onNavigateSignInClick();
    }

}
