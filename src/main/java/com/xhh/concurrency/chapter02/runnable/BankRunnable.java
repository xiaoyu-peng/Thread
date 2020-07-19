package com.xhh.concurrency.chapter02.runnable;

public class BankRunnable {

    public static void main(String[] args) {

        final TicketWindowRunnable tr = new TicketWindowRunnable();
        Thread t1 = new Thread(tr, "窗口01");
        t1.start();

        Thread t2 = new Thread(tr, "窗口02");
        t2.start();

    }
}
