package com.xhh.concurrency.basic.chapter08;

/**
 * 测试死锁
 *
 * @author PengHui
 */
public class DeadLock {

    private OtherService otherService;

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    private final Object lock = new Object();

    public void m1(){
        synchronized (lock){
            System.out.println("m1");
            otherService.s1();
        }
    }

    public void m2(){
        synchronized (lock){
            try {
                System.out.println("m2");
                otherService.s2();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
