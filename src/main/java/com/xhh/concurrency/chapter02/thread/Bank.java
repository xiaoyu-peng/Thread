package com.xhh.concurrency.chapter02.thread;

public class Bank {

    public static void main(String[] args) {
        TicketWindow window1 = new TicketWindow("窗口01");
        window1.start();

        TicketWindow window2 = new TicketWindow("窗口02");
        window2.start();
    }
}
