package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.utils.RVRendererAdapterUtils;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.lang.String.format;
import static java.util.Locale.getDefault;

/**
 * Created by nz2Dev on 28.03.2018
 */
public class GoodsRenderer extends Renderer<Goods> {

    public static RVRendererAdapter<Goods> createAdapter() {
        return new RVRendererAdapter<>(new RendererBuilder<>(new GoodsRenderer()));
    }

    @BindView(R.id.iv_goods_image) ImageView goodsImage;
    @BindView(R.id.tv_goods_name) TextView goodsName;
    @BindView(R.id.tv_goods_price) TextView goodsPriceText;
    @BindView(R.id.tv_goods_id) TextView goodsIdText;

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
        return inflater.inflate(R.layout.item_goods, parent, false);
    }

    @Override
    public void render() {
        goodsName.setText(getContent().getName());
        goodsPriceText.setText(format(getDefault(), "%.1f$", getContent().getPrice()));
        goodsIdText.setText(String.valueOf(getContent().getId()));
    }
}
