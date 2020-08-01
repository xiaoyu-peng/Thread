package com.xhh.concurrency.pattern.chapter01;

/**
 * 单例模式 - 懒加载 - 静态类  推荐指数***
 */
public class SingletonObject06 {

    private SingletonObject06(){
        // empty
    }

    /**
     * 初始化一次
     */
    private static class InstanceHolder {
        private static final SingletonObject06 instance = new SingletonObject06();
    }


    public static SingletonObject06 getInstance(){
        return InstanceHolder.instance;
    }
}
