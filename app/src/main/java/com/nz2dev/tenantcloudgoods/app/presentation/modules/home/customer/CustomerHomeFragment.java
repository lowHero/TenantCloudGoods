package com.nz2dev.tenantcloudgoods.app.presentation.modules.home.customer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class CustomerHomeFragment extends Fragment implements CustomerHomeView {

    public static CustomerHomeFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putSerializable(user.getClass().getName(), user);

        CustomerHomeFragment fragment = new CustomerHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject CustomerHomePresenter presenter;

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
        presenter.setView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @Override
    public void showAllShops(List<Shop> shops) {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public void navigateShopForCustomer(Shop shop, User customer) {
        throw new RuntimeException("Not implemented!");
    }
}