package com.xhh.concurrency.chapter07;

/**
 * synchronized 方法  this锁
 *
 * @author PengHui
 */
public class SynchronizedThis {

    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();

        new Thread("T1"){
            @Override
            public void run(){
                thisLock.m2();
            }
        }.start();

        new Thread("T2"){
            @Override
            public void run(){
                thisLock.m2();
            }
        }.start();
    }

}

class ThisLock{

    public synchronized void m1(){
        try {
            System.out.println("m1 当前线程" + Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2(){
        try {
            System.out.println("m2 当前线程" + Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}