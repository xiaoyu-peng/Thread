package com.xhh.concurrency.pattern.chapter01;

/**
 * 单例模式 - 懒加载
 */
public class SingletonObject02 {

    private static SingletonObject02 instance;

    private SingletonObject02(){
        // empty
    }

    public static SingletonObject02 getInstance(){
        if(null == instance){
            instance = new SingletonObject02();
        }
        return instance;
    }
}
