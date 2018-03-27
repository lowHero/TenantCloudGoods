package com.nz2dev.tenantcloudgoods.app.presentation.modules.shop.customer;

import com.google.gson.JsonSyntaxException;
import com.nz2dev.tenantcloudgoods.R;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.DisposableBasePresenter;
import com.nz2dev.tenantcloudgoods.app.presentation.infrastructure.PerFragment;
import com.nz2dev.tenantcloudgoods.domain.exceptions.GoodsNotFoundException;
import com.nz2dev.tenantcloudgoods.domain.interactors.orders.CreateCheckUserCase;
import com.nz2dev.tenantcloudgoods.domain.interactors.orders.CreateOrderByScannedResultUseCase;
import com.nz2dev.tenantcloudgoods.domain.models.Order;
import com.nz2dev.tenantcloudgoods.domain.models.Shop;
import com.nz2dev.tenantcloudgoods.domain.models.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.nz2dev.tenantcloudgoods.app.utils.ThrowableUtils.handleUncaught;

/**
 * Created by nz2Dev on 25.03.2018
 */
@PerFragment
class CustomerShopPresenter extends DisposableBasePresenter<CustomerShopView> {

    private final CreateOrderByScannedResultUseCase createOrderByScannedResultUseCase;
    private final CreateCheckUserCase createCheckUserCase;

    private List<Order> basket = new ArrayList<>();
    private Shop pendingShop;

    @Inject
    CustomerShopPresenter(CreateOrderByScannedResultUseCase createOrderByScannedResultUseCase, CreateCheckUserCase createCheckUserCase) {
        this.createOrderByScannedResultUseCase = createOrderByScannedResultUseCase;
        this.createCheckUserCase = createCheckUserCase;
    }

    void prepare(Shop shop) {
        pendingShop = shop;
    }

    void handleScannedResult(String result) {
        manage("Scanning", createOrderByScannedResultUseCase
                .executor(result)
                .subscribe(order -> {
                    for (Order orderInBasket : basket) {
                        if (orderInBasket.getGoods().equals(order.getGoods())) {
                            getView().showError(R.string.error_order_already_exist);
                            return;
                        }
                    }

                    basket.add(order);
                    getView().showOrder(order);
                    getView().showPossibleCheckPrice(calculatePossibleCheckPrice());
                }, throwable -> {
                    if (throwable instanceof GoodsNotFoundException) {
                        GoodsNotFoundException e = (GoodsNotFoundException) throwable;
                        getView().showError(R.string.error_goods_not_found, e.getGoodsId());
                    } else if (throwable instanceof JsonSyntaxException) {
                        getView().showError(R.string.error_invalid_scan_data);
                    } else {
                        handleUncaught(throwable);
                    }
                }));
    }

    void changeGoodsAmountInOrderClick(Order order, int goodsAmount) {
        // In this stage is possible to call some useCase that will calculate some discounts
        // or check if there enough amount etc.
        // But for now make it simple.
        if (order.getGoods().getAvailableAmount() > goodsAmount && goodsAmount >= 1) {
            order.setGoodsAmount(goodsAmount);
            order.setTotalPrice(order.getGoods().getPrice() * goodsAmount);

            getView().showOrderUpdates(order);
            getView().showPossibleCheckPrice(calculatePossibleCheckPrice());
        }
    }

    void deleteOrderFromBasket(Order orderToDelete) {
        basket.remove(orderToDelete);
        getView().showOrderDeleted(orderToDelete);
    }

    void checkoutClick() {
        manage("Creating", createCheckUserCase
                .executor(pendingShop, basket)
                .subscribe(getView()::navigateCheckout));
    }

    private float calculatePossibleCheckPrice() {
        float totalCheckPrice = 0f;
        for (Order orderToCalculate : basket) {
            totalCheckPrice += orderToCalculate.getTotalPrice();
        }
        return totalCheckPrice;
    }

}