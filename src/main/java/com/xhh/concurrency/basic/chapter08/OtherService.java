package com.xhh.concurrency.basic.chapter08;

/**
 * @author PengHui
 */
public class OtherService {

    public final static Object lock = new Object();

    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    public void s1(){
        synchronized (lock){
            System.out.println("s1..................");
        }
    }

    public void s2(){
        synchronized (lock){
            try {
                deadLock.m2();
                Thread.sleep(1000);
                System.out.println("s2..................");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
