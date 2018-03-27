package com.nz2dev.tenantcloudgoods.app.presentation.modules.home.admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.Navigator;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.home.ShopRenderer;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.app.utils.OnItemClickListener;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class AdminHomeFragment extends Fragment implements AdminHomeView, OnItemClickListener<Shop> {

    public static AdminHomeFragment newInstance(User admin) {
        Bundle args = new Bundle();
        args.putSerializable(admin.getClass().getName(), admin);

        AdminHomeFragment fragment = new AdminHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.rv_shops_list) RecyclerView shopsList;

    @Inject AdminHomePresenter presenter;
    private RVRendererAdapter<Shop> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dependencies.fromApplication(getContext())
                .createAdminHomeComponent()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter = ShopRenderer.createAdapterInstance(this);
        shopsList.setAdapter(adapter);

        User admin = (User) getArguments().getSerializable(User.class.getName());
        presenter.setView(this);
        presenter.prepareAdmin(admin);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @Override
    public void onItemClick(Shop shop) {
        presenter.selectShopClick(shop);
    }

    @OnClick(R.id.iv_account_exit)
    public void onAccountExitClick() {
        Navigator.navigateAuthorizationFrom(getActivity());
    }

    @OnClick(R.id.iv_shop_create)
    public void onCreateShopClick() {
        final EditText editText = new EditText(getContext());
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.hint_eneter_shop_name)
                .setView(editText)
                .setPositiveButton(R.string.text_ok, (dialog, which) -> {
                    presenter.createShopClick(editText.getText().toString());
                })
                .create()
                .show();
    }

    @Override
    public void showAllShops(List<Shop> shops) {
        adapter.clear();
        adapter.addAll(shops);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showShopAdded(Shop shop) {
        adapter.add(shop);
        adapter.notifyItemInserted(adapter.getItemCount() - 1);
    }

    @Override
    public void navigateShopForAdmin(Shop shop, User admin) {
        Navigator.navigateShopFrom(getActivity(), shop, admin);
    }

}