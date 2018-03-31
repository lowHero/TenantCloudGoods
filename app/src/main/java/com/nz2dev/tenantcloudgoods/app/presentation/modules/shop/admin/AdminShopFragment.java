package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.admin;

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
import android.widget.Toast;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by nz2Dev on 27.03.2018
 */
public class AdminShopFragment extends Fragment implements AdminShopView {

    private static final int RQ_SCANNING = 0x00000000;

    public static AdminShopFragment newInstance(Shop shop, User user) {
        Bundle args = new Bundle();
        args.putSerializable(shop.getClass().getName(), shop);
        args.putSerializable(user.getClass().getName(), user);

        AdminShopFragment fragment = new AdminShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_available_orders_list) RecyclerView availableOrdersList;

    @Inject AdminShopPresenter presenter;
    private RVRendererAdapter<Goods> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Dependencies.fromApplication(getContext())
                .createAdminShopComponent()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_admin, container, false);
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

        adapter = GoodsRenderer.createAdapter();
        availableOrdersList.setAdapter(adapter);

        Shop shop = (Shop) getArguments().getSerializable(Shop.class.getName());
        presenter.setView(this);
        presenter.prepare(shop);
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
                presenter.handleScannedResult(contents);
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getContext(), "Scanning canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.iv_create_goods)
    public void onScannGoodsClick() {
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

    @Override
    public void showGoods(List<Goods> goods) {
        adapter.clear();
        adapter.addAll(goods);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showNewGoods(Goods goods) {
        adapter.add(goods);
        adapter.notifyItemInserted(adapter.getItemCount() - 1);
    }
}