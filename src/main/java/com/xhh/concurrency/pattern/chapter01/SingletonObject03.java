package com.xhh.concurrency.pattern.chapter01;

/**
 * 单例模式 - 懒加载 - synchronized
 */
public class SingletonObject03 {

    private static SingletonObject03 instance;

    private SingletonObject03(){
        // empty
    }

    /**
     * 串行化，影响性能
     */
    public synchronized static SingletonObject03 getInstance(){
        if(null == instance){
            instance = new SingletonObject03();
        }
        return instance;
    }
}
