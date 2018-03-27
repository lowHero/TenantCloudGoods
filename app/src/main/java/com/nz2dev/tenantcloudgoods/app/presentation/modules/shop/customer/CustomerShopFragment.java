package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.nz2dev.tenantcloudgoods.app.presentation.modules.Navigator;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.OrderRenderer;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.checkout.CheckoutFragment;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.app.utils.RVRendererAdapterUtils;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static java.lang.String.format;
import static java.util.Locale.getDefault;

/**
 * Created by nz2Dev on 25.03.2018
 */
public class CustomerShopFragment extends Fragment implements CustomerShopView, OrderRenderer.OrderActionListener {

    private static final int RQ_SCANNING = 0x00000000;

    public static CustomerShopFragment newInstance(Shop shop, User user) {
        Bundle args = new Bundle();
        args.putSerializable(shop.getClass().getName(), shop);
        args.putSerializable(user.getClass().getName(), user);

        CustomerShopFragment fragment = new CustomerShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_possible_check_price) TextView possibleCheckPriceText;
    @BindView(R.id.rv_basket) RecyclerView basketList;

    @Inject CustomerShopPresenter presenter;
    private RVRendererAdapter<Order> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Dependencies.fromApplication(getContext())
                .createCustomerShopComponent()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_customer, container, false);
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

        adapter = OrderRenderer.createAdapter(this);
        basketList.setAdapter(adapter);

        Shop shopFromArguments = (Shop) getArguments().getSerializable(Shop.class.getName());
        presenter.setView(this);
        presenter.prepare(shopFromArguments);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RQ_SCANNING) {
            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                presenter.handleScanningResult(contents);
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getContext(), "Scanning canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onOrderAmountChanged(Order order, int amount) {
        presenter.changeGoodsAmountInOrderClick(order, amount);
    }

    @Override
    public void onOrderDeleted(Order order) {
        presenter.deleteOrderFromBasket(order);
    }

    @OnClick(R.id.btn_scan_for_goods_identify)
    public void onScanGoodsClick() {
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, RQ_SCANNING);
        } catch (Exception e) {
            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);
        }
    }

    @OnClick(R.id.btn_checkout)
    public void onCheckoutClick() {
        presenter.checkoutClick();
    }

    @Override
    public void showError(int templateId, Object... templateParams) {
        String text = String.format(Locale.getDefault(), getContext().getString(templateId), templateParams);
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPossibleCheckPrice(float price) {
        possibleCheckPriceText.setText(format(getDefault(), "%.1f$", price));
    }

    @Override
    public void showOrder(Order order) {
        adapter.add(order);
        adapter.notifyItemInserted(adapter.getItemCount() - 1);
    }

    @Override
    public void showOrderUpdates(Order order) {
        int orderPosition = RVRendererAdapterUtils.findIndexOf(order, adapter);
        Order orderInAdapter = adapter.getItem(orderPosition);
        orderInAdapter.set(order);
        adapter.notifyItemChanged(orderPosition);
    }

    @Override
    public void showOrderDeleted(Order orderToDelete) {
        int orderPosition = RVRendererAdapterUtils.findIndexOf(orderToDelete, adapter);
        adapter.remove(adapter.getItem(orderPosition));
        adapter.notifyItemRemoved(orderPosition);
    }

    @Override
    public void navigateCheckout(Check check) {
        getFragmentManager().beginTransaction()
                .replace(R.id.fl_activity_content, CheckoutFragment.newInstance(check))
                .addToBackStack(null)
                .commit();
    }

}