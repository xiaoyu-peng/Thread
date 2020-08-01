package com.xhh.concurrency.pattern.chapter01;

import java.util.stream.IntStream;

/**
 * 单例模式 - 懒加载 - 枚举  推荐指数*****
 * <p>
 * 枚举类型线程安全，只会被构建一次
 */
public class SingletonObject07 {

    private SingletonObject07() {
        // empty
    }

    private enum Singleton {
        INSTANCE;

        private final SingletonObject07 instance;

        Singleton() {
            instance = new SingletonObject07();
        }

        public SingletonObject07 getInstance(){
            return instance;
        }
    }


    public static SingletonObject07 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }


    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).forEach(i -> new Thread(String.valueOf(i)){
            @Override
            public void run() {
                System.out.println(SingletonObject07.getInstance());
            }
        }.start());
    }
}
