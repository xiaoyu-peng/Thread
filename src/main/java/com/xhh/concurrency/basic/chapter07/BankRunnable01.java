package com.xhh.concurrency.basic.chapter07;

public class BankRunnable01 {

    public static void main(String[] args) {

        final TicketWindowRunnable01 ticketWindow = new TicketWindowRunnable01();
        Thread t1 = new Thread(ticketWindow, "窗口01");
        Thread t2 = new Thread(ticketWindow, "窗口02");
        Thread t3 = new Thread(ticketWindow, "窗口03");
        t1.start();
        t2.start();
        t3.start();

    }
}
