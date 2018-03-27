package com.nz2dev.tenantcloudgoods.app.utils;

import com.pedrogomez.renderers.RVRendererAdapter;

/**
 * Created by nz2Dev on 27.03.2018
 */
public class RVRendererAdapterUtils {

    public static <T> int findIndexOf(T item, RVRendererAdapter<T> adapter) throws RuntimeException {
        for (int position = 0; position < adapter.getItemCount(); position++) {
            if (adapter.getItem(position).equals(item)) {
                return position;
            }
        }
        throw new RuntimeException("item index not founded! item: " + item);
    }

}
