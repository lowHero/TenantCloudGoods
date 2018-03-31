package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.domain.models.Goods;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.String.format;
import static java.util.Locale.getDefault;

/**
 * Created by nz2Dev on 26.03.2018
 */
public class OrderRenderer extends Renderer<Order> {

    private static class ExpansionManager {

        private List<Goods> lastExpandedGoods = new ArrayList<>();

        private void expansionClick(OrderRenderer renderer) {
            if (lastExpandedGoods.contains(renderer.getContent().getGoods())) {
                lastExpandedGoods.remove(renderer.getContent().getGoods());
                renderer.renderExpand(false);
            } else {
                lastExpandedGoods.add(renderer.getContent().getGoods());
                renderer.renderExpand(true);
            }
        }

        private void checkExpansion(OrderRenderer renderer) {
            renderer.renderExpand(lastExpandedGoods.contains(renderer.getContent().getGoods()));
        }
    }

    public interface OrderActionListener {

        void onOrderAmountChanged(Order order, int amount);
        void onOrderDeleted(Order order);

    }

    public static RVRendererAdapter<Order> createAdapter(OrderActionListener listener) {
        return new RVRendererAdapter<>(new RendererBuilder<>(new OrderRenderer(listener)));
    }

    @BindView(R.id.iv_goods_image) ImageView goodsImage;
    @BindView(R.id.tv_goods_name) TextView goodsNameText;
    @BindView(R.id.tv_order_goods_amount) TextView orderGoodsAmountText;
    @BindView(R.id.tv_order_total_price) TextView orderTotalPriceText;

    @BindView(R.id.cl_order_control_root) ConstraintLayout ordersControlRoot;
    @BindView(R.id.tv_order_control_quantity_amount) TextView orderControlQuantityAmountText;
    @BindView(R.id.tv_order_control_quantity_price) TextView orderControlQuantityPriceText;

    private final OrderActionListener listener;
    private final ExpansionManager expansionManager = new ExpansionManager();

    public OrderRenderer(OrderActionListener listener) {
        this.listener = listener;
    }

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {
        rootView.setOnClickListener(v -> {
            expansionManager.expansionClick(this);
        });
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_order, parent, false);
    }

    @Override
    public void setContent(Order content) {
        super.setContent(content);
        expansionManager.checkExpansion(this);
    }

    @OnClick(R.id.iv_quantity_remove)
    public void onDecreaseQuantityClick() {
        listener.onOrderAmountChanged(getContent(), getContent().getGoodsAmount() - 1);
    }

    @OnClick(R.id.iv_quantity_add)
    public void onIncreaseQuantityClick() {
        listener.onOrderAmountChanged(getContent(), getContent().getGoodsAmount() + 1);
    }

    @OnClick(R.id.iv_order_remove)
    public void onDeleteOrderClick() {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.question_are_you_shore)
                .setPositiveButton(R.string.text_yes, (dialog, which) -> {
                    listener.onOrderDeleted(getContent());
                })
                .setNegativeButton(R.string.text_no, (dialog, which) -> {
                    dialog.dismiss();
                })
                .create()
                .show();
    }

    @Override
    public void render() {
        Picasso.get().load(getContent().getGoods().getImageUrl())
                .placeholder(R.drawable.ic_goods_image_placeholder_black_72dp)
                .into(goodsImage);

        goodsNameText.setText(getContent().getGoods().getName());

        orderGoodsAmountText.setText(String.valueOf(getContent().getGoodsAmount()));
        orderTotalPriceText.setText(format(getDefault(), "%.1f$", getContent().getTotalPrice()));

        orderControlQuantityAmountText.setText(String.valueOf(getContent().getGoodsAmount()));
        orderControlQuantityPriceText.setText(format(getDefault(), "%.1f$", getContent().getGoods().getPrice()));
    }

    void renderExpand(boolean expand) {
        ordersControlRoot.setVisibility(expand ? View.VISIBLE : View.GONE);
    }

}
