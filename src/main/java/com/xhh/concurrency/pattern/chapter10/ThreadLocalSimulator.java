package com.xhh.concurrency.pattern.chapter10;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 模拟
 *
 * TODO 始终已当前线程作为key
 *
 * @author PengHui
 */
public class ThreadLocalSimulator<T> {

    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t){
        synchronized (this){
            storage.put(Thread.currentThread(), t);
        }
    }

    public T get(){
        synchronized (this){
            T value = storage.get(Thread.currentThread());
            if(null==value){
                return initValue();
            }
            return value;
        }
    }

    public T initValue() {
        return null;
    }
}
