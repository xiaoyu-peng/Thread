package com.xhh.concurrency.chapter04;

import java.sql.SQLOutput;

/**
 * @author PengHui
 */
public class ThreadInterrupt {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run(){
                while(true){
                    synchronized (MONITOR){
                        try {
                            MONITOR.wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println(Thread.interrupted());
                        }
                    }
                }
            }
        };

        t.start();
        Thread.sleep(100);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());

    }
}
