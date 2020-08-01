package com.xhh.concurrency.pattern.chapter01;

/**
 * 单例模式 - 懒加载 - double check + volatile
 *
 */
public class SingletonObject05 {

    private volatile static SingletonObject05 instance;

    private SingletonObject05(){
        // empty
    }

    public static SingletonObject05 getInstance(){
        if(null == instance){
            synchronized (SingletonObject05.class){
                if(null == instance){
                    instance = new SingletonObject05();
                }
            }
        }
        return instance;
    }
}
