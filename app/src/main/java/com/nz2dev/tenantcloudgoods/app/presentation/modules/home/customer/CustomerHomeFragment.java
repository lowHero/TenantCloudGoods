package com.nz2dev.tenantcloudgoods.app.presentation.modules.home.customer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class CustomerHomeFragment extends Fragment implements CustomerHomeView, OnItemClickListener<Shop> {

    public static CustomerHomeFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putSerializable(user.getClass().getName(), user);

        CustomerHomeFragment fragment = new CustomerHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.rv_shops_list)
    RecyclerView shopsList;

    @Inject CustomerHomePresenter presenter;
    private RVRendererAdapter<Shop> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dependencies.fromApplication(getContext())
                .createCustomerHomeComponent()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_customer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter = ShopRenderer.createAdapterInstance(this);
        shopsList.setAdapter(adapter);

        presenter.setView(this);
        presenter.prepareCustomer((User) getArguments().getSerializable(User.class.getName()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @Override
    public void onItemClick(Shop item) {
        presenter.shopClick(item);
    }

    @OnClick(R.id.iv_account_exit)
    public void onAccountExitClick() {
        Navigator.navigateAuthorizationFrom(getActivity());
    }

    @OnClick(R.id.iv_go_history)
    public void onGoHistoryClick() {
        Navigator.navigatePaymentHistoryFrom(getActivity());
    }

    @Override
    public void showAllShops(List<Shop> shops) {
        adapter.clear();
        adapter.addAll(shops);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void navigateShopForCustomer(Shop shop, User customer) {
        Navigator.navigateShopFrom(getActivity(), shop, customer);
    }
}