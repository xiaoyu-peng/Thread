package com.xhh.concurrency.basic.chapter07;

/**
 * synchronize static 方法
 * @author PengHui
 */
public class SynchronizeStatic {

    public static void main(String[] args) {
        new Thread("T1"){
            @Override
            public void run(){
                StaticLock.m1();
            }
        }.start();

        new Thread("T2"){
            @Override
            public void run(){
                StaticLock.m2();
            }
        }.start();

        new Thread("T3"){
            @Override
            public void run(){
                StaticLock.m3();
            }
        }.start();

    }

}

class StaticLock{

    static{
        synchronized (StaticLock.class){
            System.out.println("static "+ Thread.currentThread().getName());
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1(){
        System.out.println("m1 "+ Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2(){
        System.out.println("m2 "+ Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3(){
        System.out.println("m3 "+ Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
