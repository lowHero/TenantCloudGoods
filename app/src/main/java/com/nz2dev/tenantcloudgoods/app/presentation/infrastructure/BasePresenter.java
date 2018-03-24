package com.nz2dev.tenantcloudgoods.app.presentation.infrastructure;

import android.support.v4.app.Fragment;

/**
 * Created by nz2Dev on 24.03.2018
 */
public abstract class BasePresenter<V> {

    private V view;

    /**
     * should be called from fragment somewhere inside {@link Fragment#onActivityCreated} method
     * where it's already properly loaded and can receive calls
     */
    public final void setView(V view) {
        if (this.view != null) {
            throw new RuntimeException("view is already set");
        }
        this.view = view;
        onViewReady();
    }

    /**
     * should be called from fragment somewhere inside {@link Fragment#onDestroy} method
     * where it's detached from activity and can't receive call anymore
     */
    public final void detachView() {
        view = null;
        onViewRecycled();
    }

    public boolean isViewAttached() {
        return view != null;
    }

    protected void onViewReady() {
        // no-op
    }

    protected void onViewRecycled() {
        // no-op
    }

    protected V getView() {
        return view;
    }

}
