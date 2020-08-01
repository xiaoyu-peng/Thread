package com.xhh.concurrency.basic.chapter07;

/**
 * @author PengHui
 */
public class TicketWindowRunnable implements  Runnable {

    private int index = 1;
    private final int MAX = 500;

    private final Object MONITOR = new Object();

    public void run() {
        while (true) {

            // 同步代码块——串行化执行，影响效率
            synchronized (MONITOR){
                if (index>MAX){
                    break;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前柜台 " + Thread.currentThread().getName() + "，当前号码是：" + index++);
            }

        }
    }


}
