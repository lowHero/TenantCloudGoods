package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.checkout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nz2Dev on 26.03.2018
 */
public class CheckoutFragment extends Fragment implements CheckoutView {

    public static CheckoutFragment newInstance(Check check, User user) {
        Bundle args = new Bundle();
        args.putSerializable(check.getClass().getName(), check);
        args.putSerializable(user.getClass().getName(), user);

        CheckoutFragment fragment = new CheckoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_check_amount) TextView checkAmountText;
    @BindView(R.id.rv_orders_checkout_list) RecyclerView checkoutOrdersList;

    @Inject CheckoutPresenter presenter;
    private RVRendererAdapter<Order> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Dependencies.fromApplication(getContext())
                .createCheckoutComponent()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        adapter = OrderSimpleRenderer.createAdapter();
        checkoutOrdersList.setAdapter(adapter);

        Check check = (Check) getArguments().getSerializable(Check.class.getName());
        User user = (User) getArguments().getSerializable(User.class.getName());

        presenter.setView(this);
        presenter.prepare(check, user);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
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

    @OnClick(R.id.btn_confirm)
    public void onConfirmClick() {
        presenter.confirmCheckout();
    }

    @Override
    public void showError(int templateId, Object... templateParams) {
        String text = String.format(Locale.getDefault(), getContext().getString(templateId), templateParams);
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCheckAmount(float amount) {
        checkAmountText.setText(String.format(Locale.getDefault(), "%.1f$", amount));
    }

    @Override
    public void showCheckOrders(List<Order> orders) {
        adapter.clear();
        adapter.addAll(orders);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showScan(String checkData) {
        getFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getFragmentManager().beginTransaction()
                .replace(R.id.fl_activity_content, CheckoutScanFragment.newInstance(checkData))
                .commit();
    }

}