package com.xhh.concurrency.pattern.chapter01;

import com.xhh.concurrency.basic.chapter07.SynchronizedTest;

/**
 * 单例模式 - 懒加载 - double check
 *
 * 缺点： 存在空指针异常
 */
public class SingletonObject04 {

    private static SingletonObject04 instance;

    private SingletonObject04(){
        // empty
    }

    public static SingletonObject04 getInstance(){
        if(null == instance){
            synchronized (SingletonObject04.class){
                if(null == instance){
                    instance = new SingletonObject04();
                }
            }
        }
        return instance;
    }
}
