package com.xhh.concurrency.chapter04;

/**
 * 测试
 *
 * 守护线程随主线程结束
 */
public class DaemonThread {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            Thread innerThread = new Thread(() -> {
                while(true){
                    System.out.println("DO some thing for health check.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            innerThread.setDaemon(true); // 设置守护线程
            innerThread.setName("innerT");
            innerThread.start();

            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " Thread finish done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.setName("T");
        t.start();

        System.out.println(Thread.currentThread().getName() + " Thread start");
    }
}
