package com.nz2dev.tenantcloudgoods.app.presentation.renderers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.utils.OnItemClickListener;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

/**
 * Created by nz2Dev on 25.03.2018
 */
public class ShopRenderer extends Renderer<Shop> {

    public static RVRendererAdapter<Shop> createAdapterInstance(OnItemClickListener<Shop> listener) {
        return new RVRendererAdapter<>(new RendererBuilder<>(new ShopRenderer(listener)));
    }

    private TextView shopName;

    private final OnItemClickListener<Shop> listener;

    private ShopRenderer(OnItemClickListener<Shop> listener) {
        this.listener = listener;
    }

    @Override
    protected void setUpView(View rootView) {
        shopName = rootView.findViewById(R.id.tv_shop_name);
    }

    @Override
    protected void hookListeners(View rootView) {
        rootView.setOnClickListener(v -> listener.onItemClick(getContent()));
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_shop, parent, false);
    }

    @Override
    public void render() {
        shopName.setText(getContent().getName());
    }

}
