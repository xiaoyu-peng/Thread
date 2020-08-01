package com.xhh.concurrency.pattern.chapter01;

/**
 * 单例模式 - 饿汉式
 */
public class SingletonObject01 {

    /**
     * 缺点： 不能懒加载
     */
    private static final SingletonObject01 instance = new SingletonObject01();

    private SingletonObject01(){
        // empty
    }

    public static SingletonObject01 getInstance(){
        return instance;
    }
}
