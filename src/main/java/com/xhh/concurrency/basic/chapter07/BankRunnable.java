package com.xhh.concurrency.basic.chapter07;

public class BankRunnable {

    public static void main(String[] args) {

        final TicketWindowRunnable tr = new TicketWindowRunnable();
        Thread t1 = new Thread(tr, "窗口01");
        Thread t2 = new Thread(tr, "窗口02");
        Thread t3 = new Thread(tr, "窗口03");
        t1.start();
        t2.start();
        t3.start();

    }
}
