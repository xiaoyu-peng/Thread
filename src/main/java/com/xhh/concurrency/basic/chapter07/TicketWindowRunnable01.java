package com.xhh.concurrency.basic.chapter07;

/**
 * synchronized 同步方法
 * @author PengHui
 */
public class TicketWindowRunnable01 implements Runnable {

    /**
     * 共享变量index，存在线程安全问题
     */
    private int index = 1;

    /**
     * read only
     */
    private final int MAX = 50;


    @Override
    public void run(){
        while(true){
            if(ticket()){
                break;
            }
        }
    }


    private synchronized boolean ticket() {

        // 1. 读操作 getField
        if(index > MAX){
            return true;
        }

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 2. 写操作 index++
        //           index = index + 1;   1. get field index     2. index = index + 1      3. put field index

        System.out.println(Thread.currentThread().getName() + " 的号码是："+ index++);

        return false;

    }
}
