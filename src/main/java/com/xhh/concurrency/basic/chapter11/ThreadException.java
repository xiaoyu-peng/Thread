package com.xhh.concurrency.basic.chapter11;

import java.net.SocketTimeoutException;

/**
 * 测试钩子程序，获取程序中断信号
 */
public class ThreadException {

    private static final int A = 10;
    private static final int B = 0;

    public static void main(String[] args) {

        Thread t = new Thread( () -> {
            try {
                Thread.sleep(5_000);
                int result = A/B;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.setUncaughtExceptionHandler( (thread,e) -> {
            System.out.println(e);
            System.out.println(thread);
        });

        t.start();
    }
}
