package com.waltersun.lastesttech.service.impl;

import java.util.function.Supplier;

/**
 * @author walter
 * @date 2021-11-09 11:17
 */
public class LazySupplier<T> implements Supplier<T> {

    private final Supplier<? extends T> supplier;

    // 利用 value 属性缓存 supplier 计算后的值
    private T value;

    private LazySupplier(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    public static <T> LazySupplier<T> of(Supplier<? extends T> supplier) {
        return new LazySupplier<>(supplier);
    }

    public T get() {
        if (value == null) {
            T newValue = supplier.get();

            if (newValue == null) {
                throw new IllegalStateException("Lazy value can not be null!");
            }

            value = newValue;
        }

        return value;
    }
}