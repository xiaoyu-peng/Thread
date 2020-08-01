package com.xhh.concurrency.basic.chapter09;

import java.util.stream.Stream;

/**
 *
 * 生产者和消费者 版本三    while循环 + notifyAll()
 *
 * @author PengHui
 */
public class ProductConsumer03 {

    private int i = 1;

    final Object lock = new Object();

    private volatile boolean isProduced = true;


    // 生产者
    public void produce(){
        synchronized (lock){
            while (isProduced){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            i++;
            System.out.println(Thread.currentThread().getName() +" p-> "+ i);
            lock.notifyAll();
            isProduced = true;
        }
    }

    // 消费者
    public void consume(){
        synchronized (lock){

            while(!isProduced){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() +" c-> "+ i);
            lock.notifyAll();
            isProduced = false;
        }

    }


    public static void main(String[] args) {
        ProductConsumer03 pc = new ProductConsumer03();

        Stream.of("p1", "p2").forEach(n -> {
            new Thread(n) {
                @Override
                public void run() {
                    while (true) {
                        pc.produce();
                    }
                }
            }.start();
        });


        Stream.of("c1", "c2").forEach(n -> {
            new Thread(n) {
                @Override
                public void run() {
                    while (true) {
                        pc.consume();
                    }
                }
            }.start();
        });
    }


}
