package com.xhh.concurrency.chapter12;

import javax.sound.midi.Soundbank;

public class ThreadGroupCreate {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getName());

        ThreadGroup gp1 = new ThreadGroup("GP1");
        Thread t1 = new Thread(gp1, "t1"){
            @Override
            public void run(){
                while (true){
                    try {
                        Thread.sleep(1_000);
                        System.out.println(getThreadGroup().getName());
                        System.out.println(getThreadGroup().getParent());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t1.start();

    }
}
