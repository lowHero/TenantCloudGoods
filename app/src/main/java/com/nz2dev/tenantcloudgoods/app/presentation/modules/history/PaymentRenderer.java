package com.nz2dev.tenantcloudgoods.app.presentation.modules.history;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.checkout.OrderSimpleRenderer;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.nz2dev.tenantcloudgoods.domain.models.Payment;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nz2Dev on 27.03.2018
 */
public class PaymentRenderer extends Renderer<Payment> {

    public static RVRendererAdapter<Payment> createAdapter() {
        return new RVRendererAdapter<>(new RendererBuilder<>(new PaymentRenderer()));
    }

    @BindView(R.id.tv_shop_name) TextView shopName;
    @BindView(R.id.tv_check_price) TextView checkPriceText;
    @BindView(R.id.tv_payment_date) TextView paymentDate;

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {
        rootView.setOnClickListener(v -> {
            ShowOrdersDialog dialog = new ShowOrdersDialog(getContext(), getContent().getCheck().getOrders());
            dialog.show();
        });
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_payment_details, parent, false);
    }

    @Override
    public void render() {
        shopName.setText(getContent().getCheck().getShop().getName());
        checkPriceText.setText(String.format(Locale.getDefault(), "-%.1f$", getContent().getCheck().getPrice()));
        paymentDate.setText(DateFormat.format("yyyy/MM/dd", getContent().getTime()));
    }

    private static class ShowOrdersDialog extends AlertDialog {

        private RecyclerView ordersList;

        private ShowOrdersDialog(@NonNull Context context, List<Order> orders) {
            super(context);
            View root = LayoutInflater.from(context).inflate(R.layout.unit_orders_list, null);
            setView(root);

            RVRendererAdapter<Order> adapter = OrderSimpleRenderer.createAdapter();
            ordersList = root.findViewById(R.id.rv_orders_checkout_list);
            ordersList.setAdapter(adapter);

            adapter.addAll(orders);
            adapter.notifyDataSetChanged();
        }

    }

}
