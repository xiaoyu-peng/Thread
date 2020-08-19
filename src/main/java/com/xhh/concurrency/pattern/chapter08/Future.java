package com.xhh.concurrency.pattern.chapter08;

/**
 * @author PengHui
 */
public interface Future<T> {

    T get() throws InterruptedException;
}
