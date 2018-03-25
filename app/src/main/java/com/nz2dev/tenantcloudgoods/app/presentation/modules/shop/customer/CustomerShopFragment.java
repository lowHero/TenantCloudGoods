package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.Navigator;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.OrderRenderer;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.pedrogomez.renderers.RVRendererAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nz2Dev on 25.03.2018
 */
public class CustomerShopFragment extends Fragment implements CustomerShopView, OrderRenderer.OrderActionListener {

    public static CustomerShopFragment newInstance(Shop shop, User user) {
        Bundle args = new Bundle();
        args.putSerializable(shop.getClass().getName(), shop);
        args.putSerializable(user.getClass().getName(), user);

        CustomerShopFragment fragment = new CustomerShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.rv_basket) RecyclerView basketList;

    @Inject CustomerShopPresenter presenter;
    private RVRendererAdapter<Order> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        adapter = OrderRenderer.createAdapter(this);
        basketList.setAdapter(adapter);

        presenter.setView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @OnClick(R.id.btn_scan_for_goods_identify)
    public void onScanGoodsClick() {
        presenter.scanClick();
    }

    @OnClick(R.id.btn_checkout)
    public void onCheckoutClick() {
        presenter.checkoutClick();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            presenter.handleScanningResult(result);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onOrderAmountChanged(Order order, int amount) {
        throw new RuntimeException("Not implemented! TODO write presenter handler");
    }

    @Override
    public void onOrderDeleted(Order order) {
        throw new RuntimeException("Not implemented! TODO write presenter handler");
    }

    @Override
    public void showOrder(Order order) {
        adapter.add(order);
        adapter.notifyItemInserted(adapter.getItemCount() - 1);
    }

    @Override
    public void showScanningCanceled() {
        Toast.makeText(getContext(), "Scanning canceled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGoodsNotFound(int goodsId) {
        Toast.makeText(getContext(), "goods: " + goodsId + " not found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInvalidScanData() {
        Toast.makeText(getContext(), "invalid data!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateScanning() {
        IntentIntegrator.forSupportFragment(this)
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .setOrientationLocked(false)
                .setBeepEnabled(false)
                .initiateScan();
    }

    @Override
    public void navigateCheckout(Check check) {
        Navigator.navigateCheckoutFrom(getActivity(), check);
    }

}