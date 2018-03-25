package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.utils.OnItemClickListener;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nz2Dev on 26.03.2018
 */
public class OrderRenderer extends Renderer<Order> {

    public interface OrderActionListener {

        void onOrderAmountChanged(Order order, int amount);
        void onOrderDeleted(Order order);

    }

    public static RVRendererAdapter<Order> createAdapter(OrderActionListener listener) {
        return new RVRendererAdapter<>(new RendererBuilder<>(new OrderRenderer(listener)));
    }

    @BindView(R.id.tv_goods_name) TextView goodsNameText;
    @BindView(R.id.tv_goods_price) TextView goodsPriceText;
    @BindView(R.id.tv_goods_amount) TextView goodsAmountText;

    private OrderActionListener listener;

    public OrderRenderer(OrderActionListener listener) {
        this.listener = listener;
    }

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {
        // TODO define trigger for action listener.
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_order, parent, false);
    }

    @Override
    public void render() {
        goodsNameText.setText(getContent().getGoods().getName());
        goodsPriceText.setText(String.format(Locale.getDefault(), "%.1f$", getContent().getGoods().getPrice()));
        goodsAmountText.setText(String.valueOf(getContent().getAmount()));
    }

}
