package com.xhh.concurrency.chapter03;

import javax.sound.midi.Soundbank;

/**
 * @author PengHui
 */
public class CreateDaemonThread {

    public static void main(String[] args) {

        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " running");
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " stop");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }; // new

        //设置守护线程, main线程声明周期结束，守护线程一起结束
        t.setDaemon(true);

        t.start(); // runnable  -> running | -> blocked | -> dead

        System.out.println(Thread.currentThread().getName());
    }
}
