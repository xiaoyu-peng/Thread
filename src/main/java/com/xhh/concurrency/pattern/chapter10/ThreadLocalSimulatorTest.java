package com.xhh.concurrency.pattern.chapter10;

import java.util.Random;

public class ThreadLocalSimulatorTest<S> {

    private final static ThreadLocalSimulator<String> threadLocal = new ThreadLocalSimulator<String>(){
        @Override
        public String initValue() {
            return "No Value";
        }
    };

    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            threadLocal.set("ThreadLocal-01");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + "  " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set("ThreadLocal-02");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + "  " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("===============");
        System.out.println(Thread.currentThread().getName() + "  " + threadLocal.get());
    }
}