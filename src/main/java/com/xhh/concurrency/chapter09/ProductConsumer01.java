package com.xhh.concurrency.chapter09;

/**
 *
 * 生产者和消费者 版本一 单线程情况下正常
 * @author xhhui
 */
public class ProductConsumer01 {

    private int i = 0;

    final Object lock = new Object();

    private volatile boolean isProduced = true;


    // 生产者
    public void produce(){
        synchronized (lock){
            if(isProduced){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                i++;
                System.out.println("p-> "+ i);
                lock.notify();
                isProduced = true;
            }
        }
    }

    // 消费者
    public void consume(){
        synchronized (lock){
            if(isProduced){
                System.out.println("c-> "+ i);
                lock.notify();
                isProduced = false;
            }else{
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String[] args) {
        ProductConsumer01 pc = new ProductConsumer01();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    pc.produce();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    pc.consume();
                }
            }
        }.start();
    }
}
