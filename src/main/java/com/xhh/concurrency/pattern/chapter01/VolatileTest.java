package com.xhh.concurrency.pattern.chapter01;

/**
 * volatile 关键字
 *
 *  1. 数据总线加锁
 *  2. CPU高效缓存一致性协议
 *
 *      核心思想：
 *          1） 当CPU写入数据的时候，如果发现该变量被共享（即，在其他cpu中也存在该变量的副本）， 会发出一个信息，通知其他CPU该变量的缓存无效
 *          2） 当其他cpu访问该变量的时候，需要重新到主内存进行获取
 *
 */
public class VolatileTest {

    private volatile static int INIT_VALUE = 0;

    private final static int MAX_VALUE = 50;

    public static void main(String[] args) {

        new Thread( () -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_VALUE){
                if(localValue != INIT_VALUE){
                    System.out.printf("The localValue updated to [%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "READER").start();

        new Thread( () -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_VALUE){
                System.out.printf("Updated INIT_VALUE to [%d]\n", ++ localValue);
                INIT_VALUE = localValue;

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();

    }
}
