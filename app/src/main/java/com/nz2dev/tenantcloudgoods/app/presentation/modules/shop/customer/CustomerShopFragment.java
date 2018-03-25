package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.domain.models.Check;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nz2Dev on 25.03.2018
 */
public class CustomerShopFragment extends Fragment implements CustomerShopView {

    public static CustomerShopFragment newInstance(Shop shop, User user) {
        Bundle args = new Bundle();
        args.putSerializable(shop.getClass().getName(), shop);
        args.putSerializable(user.getClass().getName(), user);

        CustomerShopFragment fragment = new CustomerShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject CustomerShopPresenter presenter;

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
    public void showGoods(Goods goods) {
        Toast.makeText(getContext(), "goods: " + goods + " was added", Toast.LENGTH_SHORT).show();
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
    public void showBarcodeUnsupported() {
        Toast.makeText(getContext(), "barcode not supported!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateScanning() {
        IntentIntegrator.forSupportFragment(this)
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .setBeepEnabled(false)
                .initiateScan();
    }

    @Override
    public void navigateCheckout(Check check) {
        throw new RuntimeException("Not implemented!");
    }

}