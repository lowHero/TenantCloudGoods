package com.nz2dev.tenantcloudgoods.app.presentation.modules.checkout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.utils.Dependencies;
import com.nz2dev.tenantcloudgoods.domain.models.Check;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nz2Dev on 26.03.2018
 */
public class CheckoutFragment extends Fragment implements CheckoutView {

    public static CheckoutFragment newInstance(Check check) {
        Bundle args = new Bundle();
        args.putSerializable(check.getClass().getName(), check);

        CheckoutFragment fragment = new CheckoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.tv_check_amount) TextView checkAmountText;
    @BindView(R.id.iv_checkout_code) ImageView checkCodeImage;

    @Inject CheckoutPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        presenter.setView(this);
        presenter.prepare((Check) getArguments().getSerializable(Check.class.getName()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @Override
    public void showGeneratedData(Bitmap checkBitmap) {
        checkCodeImage.setImageBitmap(checkBitmap);
    }

    @Override
    public void showCheckAmount(float amount) {
        checkAmountText.setText(String.format(Locale.getDefault(), "%.1f$", amount));
    }

    @Override
    public void showGenerationFailed() {
        Toast.makeText(getContext(), "generation failed", Toast.LENGTH_SHORT).show();
    }

}