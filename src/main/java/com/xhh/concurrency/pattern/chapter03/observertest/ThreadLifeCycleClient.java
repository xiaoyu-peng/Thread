package com.xhh.concurrency.pattern.chapter03.observertest;

import java.util.Arrays;

/**
 * 使用观察者模式 查看线程的生命周期
 * @author PengHui
 */
public class ThreadLifeCycleClient {

    public static void main(String[] args) {
        new LifeCycleObserver().concurrentQuery(Arrays.asList("1", "2"));
    }
}
