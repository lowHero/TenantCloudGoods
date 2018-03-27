package com.nz2dev.tenantcloudgoods.app.presentation.modules.checkout.scan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.utils.QRCodeHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nz2Dev on 27.03.2018
 */
public class CheckoutScanFragment extends Fragment {

    private static final String KEY_CHECK_DATA = "check";

    public static CheckoutScanFragment newInstance(String serializedCheckData) {
        Bundle args = new Bundle();
        args.putString(KEY_CHECK_DATA, serializedCheckData);

        CheckoutScanFragment fragment = new CheckoutScanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_checkout_scan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String serializedCheckData = getArguments().getString(KEY_CHECK_DATA);
        ImageView checkQRCodeImage = view.findViewById(R.id.iv_check_qr_code);
        checkQRCodeImage.setImageBitmap(QRCodeHelper.encodeToBitmap(serializedCheckData, 512, 512));
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

}