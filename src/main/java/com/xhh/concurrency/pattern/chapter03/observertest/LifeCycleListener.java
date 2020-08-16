package com.xhh.concurrency.pattern.chapter03.observertest;

/**
 * @author PengHui
 */
public interface LifeCycleListener {
    void onEvent(ObservableRunnable.RunnableEvent event);
}
