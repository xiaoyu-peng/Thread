package com.xhh.concurrency.basic.chapter10;

/**
 * @author PH
 * @date 2020/7/27 20:09
 * @description Synchronized
 */
public class SynchronizedProblem {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                SynchronizedProblem.work();
            }
        };
        t1.start();

        Thread.sleep(5_000);

        Thread t2 = new Thread(){
            @Override
            public void run() {
                SynchronizedProblem.work();
            }
        };
        t2.start();

        Thread.sleep(2_000);
        t2.interrupt();
        System.out.println("线程无法被中断： " + t2.isInterrupted());
    }

    private synchronized static void work() {
        System.out.println(Thread.currentThread().getName());
        while (true){

        }
    }
}
