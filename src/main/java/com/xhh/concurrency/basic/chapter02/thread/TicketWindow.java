package com.xhh.concurrency.basic.chapter02.thread;


/**
 * @author PengHui
 */
public class TicketWindow extends Thread {

    // static 修饰，生命周期较长
    private static final int MAX = 50;

    private static int index = 1;

    private String name;

    public TicketWindow(String name) {
        name = this.name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("当前柜台 " + this.getName() + "，当前号码是：" + index++);
        }
    }

}
