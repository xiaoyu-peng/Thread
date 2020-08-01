package com.xhh.concurrency.basic.chapter02.runnable;

/**
 * @author PengHui
 */
public class TicketWindowRunnable implements  Runnable {

    private final int MAX = 50;

    private int index = 1;

    public void run() {
        while (index <= MAX) {
            System.out.println("当前柜台 " + Thread.currentThread().getName() + "，当前号码是：" + index++);
        }
    }


}
