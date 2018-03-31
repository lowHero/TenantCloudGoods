package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.checkout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.lang.String.format;
import static java.util.Locale.getDefault;

/**
 * Created by nz2Dev on 27.03.2018
 */
public class OrderSimpleRenderer extends Renderer<Order> {

    public static RVRendererAdapter<Order> createAdapter() {
        return new RVRendererAdapter<>(new RendererBuilder<>(new OrderSimpleRenderer()));
    }

    @BindView(R.id.iv_goods_image) ImageView goodsImage;
    @BindView(R.id.tv_goods_name) TextView goodsName;
    @BindView(R.id.tv_order_total_price) TextView orderTotalPriceText;
    @BindView(R.id.tv_order_goods_amount) TextView orderGoodsAmountText;

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {
        // no-op
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_order_simple, parent, false);
    }

    @Override
    public void render() {
        Picasso.get().load(getContent().getGoods().getImageUrl())
                .placeholder(R.drawable.ic_goods_image_placeholder_black_72dp)
                .into(goodsImage);

        goodsName.setText(getContent().getGoods().getName());
        orderGoodsAmountText.setText(String.valueOf(getContent().getGoodsAmount()));
        orderTotalPriceText.setText(format(getDefault(), "%.1f$", getContent().getTotalPrice()));
    }

}
