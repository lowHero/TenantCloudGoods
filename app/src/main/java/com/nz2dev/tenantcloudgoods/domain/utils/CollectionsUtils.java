package com.nz2dev.tenantcloudgoods.domain.utils;

import java.util.Collection;
import java.util.HashMap;

import io.reactivex.functions.Function;

/**
 * Created by nz2Dev on 25.03.2018
 */
public class CollectionsUtils {

    public static <T, K> HashMap<K, T> toHashMap(Collection<T> collection, Function<T, K> keySelector) {
        try {
            HashMap<K, T> hashMap = new HashMap<>();
            for (T item : collection) {
                hashMap.put(keySelector.apply(item), item);
            }
            return hashMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
